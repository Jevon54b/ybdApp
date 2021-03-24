package com.bingo.ybd.modules.mine.activity

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import androidx.lifecycle.Observer
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.constant.Constant.ARQC_ADDRESS_INFO
import com.bingo.ybd.data.model.PointInfo
import com.bingo.ybd.ext.errorToast
import com.bingo.ybd.ext.successToast
import com.bingo.ybd.modules.mine.vm.MineViewModel
import kotlinx.android.synthetic.main.activity_address_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddressDetailActivity : BaseVMActivity(){
    override fun getLayoutId(): Int = R.layout.activity_address_detail

    override fun getViewModel(): BaseViewModel = mineViewModel

    private val mineViewModel: MineViewModel by viewModel()

    private var type = ""
    private var addressId = 0

    private lateinit var pointInfo: PointInfo

    override fun initView() {
        chooseAddressText.setOnClickListener {
            val intent = Intent(this,MapActivity::class.java)
            startActivityForResult(intent,ARQC_ADDRESS_INFO)
        }
        saveBtn.setOnClickListener {
            if (TextUtils.isEmpty(userNameEdit.text)){
                errorToast("请输入收货人姓名")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(phoneEdit.text)){
                errorToast("请输入电话号码")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(chooseAddressText.text)){
                errorToast("请选择收货地址")
            }
            if (type == Constant.VALUE_ADDRESS_CREATE){
                mineViewModel.createAddressInfo(
                    chooseAddressText.text.toString(),
                    phoneEdit.text.toString(),
                    userNameEdit.text.toString(),
                    pointInfo.latitude,
                    pointInfo.longitude
                ).observe(this, Observer {
                    if (it.status == "200"){
                        finish()
                        successToast(it.msg)
                    }else{
                        errorToast(it.msg)
                    }
                })
            }else{
                mineViewModel.updateAddressInfo(
                    addressId,
                    chooseAddressText.text.toString(),
                    phoneEdit.text.toString(),
                    userNameEdit.text.toString(),
                    pointInfo.latitude,
                    pointInfo.longitude
                ).observe(this, Observer {
                    if (it.status == "200"){
                        finish()
                        successToast(it.msg)
                    }else{
                        errorToast(it.msg)
                    }
                })
            }
        }
    }

    override fun initData() {
        intent.getStringExtra(Constant.KEY_ADDRESS_DETAIL_TYPE)?.let{
            type = it
        }
        addressId = intent.getIntExtra("id",0)
        if (type == Constant.VALUE_ADDRESS_CREATE){
            addressPageTitleText.text = "新增收货地址"
        }else{
            addressPageTitleText.text = "修改收货地址"
        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ARQC_ADDRESS_INFO && resultCode == Activity.RESULT_OK){
            val pointInfo = data?.getParcelableExtra<PointInfo>(Constant.KEY_ADDRESS_INFO)
            pointInfo?.let {
                chooseAddressText.setText(it.poiName)
                this.pointInfo = it
            }
        }
    }
}