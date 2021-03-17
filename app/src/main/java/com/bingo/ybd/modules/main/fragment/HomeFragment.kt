package com.bingo.ybd.modules.main.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bingo.ybd.R
import com.bingo.ybd.base.fragment.BaseVMFragment
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.data.model.MedBrief
import com.bingo.ybd.ext.errorToast
import com.bingo.ybd.modules.main.activity.MedListActivity
import com.bingo.ybd.modules.main.custom.ImageBannerAdapter
import com.bingo.ybd.modules.main.custom.MedShowAdapter
import com.bingo.ybd.modules.main.vm.MainViewModel
import com.bingo.ybd.util.StringUtils
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment:BaseVMFragment(),View.OnClickListener {

    companion object{
        const val TAG = "HomeFragment"
    }

    lateinit var medList: MutableList<MedBrief>

    private val mainViewModel: MainViewModel by viewModel()

    lateinit var medShowAdapter: MedShowAdapter

    lateinit var imageBannerAdapter: ImageBannerAdapter

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun getViewModel(): BaseViewModel = mainViewModel

    override fun initView() {
        initClickListener()
        initBanner()
        initRecyclerView()
        initSearchEditClickListener()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initSearchEditClickListener() {
        searchEdit.setOnTouchListener { view, event ->
            val viewHeight = view.bottom - view.top
            val viewWidth = view.right - view.left
            val scaleY = 0.075
            val scaleX = 0.92
            if (event.x > viewWidth * scaleX && event.y > viewHeight* scaleY && event.y < viewHeight*(1-scaleY)){
                if (TextUtils.isEmpty(searchEdit.text)){
                   errorToast("输入为空")
                }else{
                    val keyword = searchEdit.text.toString()
                    val intent = Intent(requireContext(),MedListActivity::class.java)
                    intent.putExtra(Constant.KEY_MED_LIST_CODE,Constant.VALUE_SEARCH_LIST)
                    intent.putExtra(Constant.KEY_SEARCH_WORD,keyword)
                    requireContext().startActivity(intent)
                }
            }
            false
        }
    }

    private fun initRecyclerView() {
        medShowAdapter = MedShowAdapter(requireContext())
        recyclerView.adapter = medShowAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initBanner() {
        medList = ArrayList()
        imageBannerAdapter = ImageBannerAdapter(requireContext(), medList)
        banner.adapter = imageBannerAdapter
        banner.indicator = CircleIndicator(requireContext())
        banner.start()
    }

    private fun initClickListener() {
        btn_fever.setOnClickListener(this)
        btn_children.setOnClickListener(this)
        btn_cough.setOnClickListener(this)
        btn_daily.setOnClickListener(this)
        btn_face.setOnClickListener(this)
        btn_prescript.setOnClickListener(this)
        btn_sexes.setOnClickListener(this)
        btn_skin.setOnClickListener(this)
        btn_stomach.setOnClickListener(this)
        btn_tonic.setOnClickListener(this)
        btn_twist.setOnClickListener(this)
        btn_vitamin.setOnClickListener(this)
    }

    override fun initData() {
        mainViewModel.getTop5MedList().observe(this, Observer {
            medShowAdapter.replaceAll(it.data)
            medShowAdapter.notifyDataSetChanged()
            imageBannerAdapter.replaceAll(it.data)
            imageBannerAdapter.notifyDataSetChanged()
        })
    }

    override fun onClick(v: View?) {
        v?.let {
            val typeId = when(it.id){
                R.id.btn_fever -> 30001
                R.id.btn_sexes -> 30002
                R.id.btn_cough -> 30003
                R.id.btn_skin  -> 30004
                R.id.btn_stomach -> 30005
                R.id.btn_twist -> 30006
                R.id.btn_children -> 30007
                R.id.btn_tonic -> 30008
                R.id.btn_prescript -> 30009
                R.id.btn_daily -> 30010
                R.id.btn_face -> 30011
                R.id.btn_vitamin -> 30012
                else -> 0
            }
            val intent = Intent(requireContext(),MedListActivity::class.java)
            intent.putExtra(Constant.KEY_MED_LIST_CODE,Constant.VALUE_TYPE_LIST)
            intent.putExtra(Constant.KEY_TYPE_ID,typeId)
            requireContext().startActivity(intent)
        }
    }

    override fun onStop() {
        super.onStop()
        banner.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        banner?.destroy()
    }


}