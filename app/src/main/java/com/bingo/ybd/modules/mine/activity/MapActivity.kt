package com.bingo.ybd.modules.mine.activity

import android.app.Activity
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.search.geocode.*
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.data.model.PointInfo
import com.bingo.ybd.modules.mine.custom.PoiInfoAdapter
import com.bingo.ybd.util.BitmapUtils
import kotlinx.android.synthetic.main.activity_map.*


class MapActivity : BaseVMActivity() {

    companion object {
        const val TAG = "MapActivity"
    }

    override fun getViewModel(): BaseViewModel = BaseViewModel()
    override fun getLayoutId(): Int = R.layout.activity_map

    private var needRefreshPoi: Boolean = true
    private var mapView: MapView? = null
    private lateinit var mBaiduMap: BaiduMap
    private lateinit var mLocationClient: LocationClient

    private lateinit var mMarker: Marker
    private lateinit var bitmap: BitmapDescriptor

    private var firstLocate = true

    private lateinit var geoCoder : GeoCoder

    private lateinit var poiInfoAdapter: PoiInfoAdapter

    private val geoCoderListener = object: OnGetGeoCoderResultListener{
        override fun onGetGeoCodeResult(p0: GeoCodeResult?) {

        }

        override fun onGetReverseGeoCodeResult(p0: ReverseGeoCodeResult?) {
            p0?.let { result ->
                poiInfoAdapter.replaceAll(result.poiList.map {
                    PointInfo(it.name,it.address,it.location.latitude,it.location.longitude)
                })
                poiInfoAdapter.notifyDataSetChanged()
            }
        }

    }

    private val myLocationListener = object : BDAbstractLocationListener() {
        override fun onReceiveLocation(location: BDLocation?) {
            location?.let { bdLocation ->
                if (firstLocate) {
                    firstLocate = false
                    moveToMyLocation(bdLocation.latitude, bdLocation.longitude)
                    val reverseGeoCodeOption = ReverseGeoCodeOption();
                    // 设置反地理编码位置坐标
                    reverseGeoCodeOption.location(LatLng(bdLocation.latitude, bdLocation.longitude));
                    geoCoder.reverseGeoCode(reverseGeoCodeOption);
                }
            }
        }
    }



    private val mapDragListener = object : BaiduMap.OnMapStatusChangeListener {
        override fun onMapStatusChangeStart(p0: MapStatus?) {
            needRefreshPoi = true
            if (::mMarker.isInitialized) {
                p0?.let {
                    mMarker.position = it.target
                }
            }
        }

        override fun onMapStatusChangeStart(p0: MapStatus?, p1: Int) {
        }

        override fun onMapStatusChange(p0: MapStatus?) {
            if (::mMarker.isInitialized) {
                p0?.let {
                    mMarker.position = it.target
                }
            }
        }

        override fun onMapStatusChangeFinish(p0: MapStatus?) {
            if (::mMarker.isInitialized) {
                p0?.let {
                    mMarker.position = it.target
                    geoCoder.reverseGeoCode(ReverseGeoCodeOption().location(it.target))
                }
            }
        }
    }

    private fun moveToMyLocation(latitude: Double, longitude: Double) {
        // 构建地图状态
        val builder = MapStatus.Builder()
        val zoom = 20f
        val center = LatLng(latitude, longitude) //中点设定为定位位置
        builder.zoom(zoom)
        builder.target(center)
        mapView?.map?.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build())) //更新地图
        showMarker(center)
    }

    private fun showMarker(latLng: LatLng) {
        val oldBitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)
        val newBitmap = BitmapUtils.zoomImg(oldBitmapDescriptor.bitmap, 100, 100)
        bitmap = BitmapDescriptorFactory.fromBitmap(newBitmap)
        val markerOptions = MarkerOptions()
            .position(latLng)
            .icon(bitmap) // 设置 Marker 覆盖物的图标
            .zIndex(0) // 设置 marker 覆盖物的 zIndex
            .clickable(false) // 设置Marker是否可点击
        mMarker = mBaiduMap.addOverlay(markerOptions) as Marker
    }

    override fun initView() {
        initRecyclerView()
        initMap()
        initLocation()
        if (mLocationClient != null && mLocationClient.isStarted) {
            mLocationClient.requestLocation()
        }
    }

    private fun initRecyclerView() {
        poiInfoAdapter = PoiInfoAdapter(this)
        poiInfoRecyclerView.layoutManager = LinearLayoutManager(this)
        poiInfoRecyclerView.adapter = poiInfoAdapter
        poiInfoAdapter.setOnItemClickListener {
            val intent = intent
            intent.putExtra(Constant.KEY_ADDRESS_INFO,it)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

    private fun initMap() {
        val options = BaiduMapOptions()
        options.zoomControlsEnabled(false)
        options.scaleControlEnabled(false)
        mapView = MapView(this, options)
        mBaiduMap = mapView!!.map
        mapView!!.removeViewAt(1)
        mapFrameLayout.addView(mapView)
        mBaiduMap.setOnMapStatusChangeListener(mapDragListener);
        mBaiduMap.setMaxAndMinZoomLevel(20f,20f)
        geoCoder = GeoCoder.newInstance()
        geoCoder.setOnGetGeoCodeResultListener(geoCoderListener)
    }

    private fun initLocation() {
        mapView?.map?.isMyLocationEnabled = true

        //定位初始化
        mLocationClient = LocationClient(this)

        //通过LocationClientOption设置LocationClient相关参数
        val option = LocationClientOption()

        option.isOpenGps = true // 打开gps
        option.setCoorType("bd09ll") // 设置坐标类型
        option.setScanSpan(1000)
        //设置locationClientOption

        mLocationClient.locOption = option
        //注册LocationListener监听器
        mLocationClient.registerLocationListener(myLocationListener)
        //开启地图定位图层
        mLocationClient.start()
    }


    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroy() {
        mLocationClient.stop()
        mBaiduMap.isMyLocationEnabled = false
        mapView?.onDestroy()
        mapView = null
        bitmap.recycle()
        super.onDestroy()
    }
}