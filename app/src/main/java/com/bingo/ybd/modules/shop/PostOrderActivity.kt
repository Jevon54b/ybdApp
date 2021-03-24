package com.bingo.ybd.modules.shop

import android.app.Activity
import android.content.Intent
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.config.Settings
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.constant.Constant.ARQC_ORDER_ADDRESS
import com.bingo.ybd.constant.Constant.KEY_ADDRESS_LIST_TYPE
import com.bingo.ybd.constant.Constant.VALUE_FROM_POST_ORDER
import com.bingo.ybd.data.model.AddressInfo
import com.bingo.ybd.data.model.MedBrief
import com.bingo.ybd.data.model.MedInOrder
import com.bingo.ybd.data.model.UserOrderInfo
import com.bingo.ybd.ext.errorToast
import com.bingo.ybd.ext.successToast
import com.bingo.ybd.modules.mine.activity.AddressListActivity
import com.bingo.ybd.modules.mine.vm.MineViewModel
import com.bingo.ybd.modules.shop.custom.MedPostItemAdapter
import com.bingo.ybd.modules.shop.vm.CartViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_post_order.*
import kotlinx.android.synthetic.main.dialog_deliver_info_edit.*

import org.koin.androidx.viewmodel.ext.android.viewModel

class PostOrderActivity : BaseVMActivity() {

    val TAG = "PostOrderActivity"

    lateinit var postItemAdapter: MedPostItemAdapter

    private val cartViewModel: CartViewModel by viewModel()
    private val mineViewModel: MineViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_post_order

    override fun getViewModel(): BaseViewModel = cartViewModel

    var orderMedPrice = 0f

    override fun initView() {
        postItemAdapter = MedPostItemAdapter(this)
        recyclerView.adapter = postItemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        backImg.setOnClickListener {
            finish()
        }

        editInfoView.setOnClickListener {
            val intent = Intent(this,AddressListActivity::class.java)
            intent.putExtra(KEY_ADDRESS_LIST_TYPE, VALUE_FROM_POST_ORDER)
            startActivityForResult(intent,ARQC_ORDER_ADDRESS)
        }

        postOrderBtn.setOnClickListener {
            onPostOrderBtnClick()
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioBtn1 -> {
                    UserOrderInfo.speed = 0
                    deliverFeeText.text = "￥4"
                    totalPriceText.text = "￥${orderMedPrice + 4}"
                }
                R.id.radioBtn2 -> {
                    UserOrderInfo.speed = 1
                    deliverFeeText.text = "￥2"
                    totalPriceText.text = "￥${orderMedPrice + 2}"
                }
            }
        }
    }

    override fun initData() {
        val medList = intent.getParcelableArrayListExtra<MedInOrder>(Constant.KEY_CART_LIST)
        postItemAdapter.addAll(medList)
        postItemAdapter.notifyDataSetChanged()
        orderMedPrice = medList[0].totalSum
        orderMedPriceText.text = "￥$orderMedPrice"
        totalPriceText.text = "￥$orderMedPrice"

        mineViewModel.getLastUseAddressInfo().observe(this, Observer {
            if(it.status == "200"){
                nameText.text = it.data.userName
                phoneText.text = it.data.phone
                addressText.text = it.data.address
                UserOrderInfo.name = it.data.userName
                UserOrderInfo.phone = it.data.phone
                UserOrderInfo.address = it.data.address
                UserOrderInfo.addressId = it.data.id
            }
        })
    }

    private fun onPostOrderBtnClick() {
        if (UserOrderInfo.name.isBlank() || UserOrderInfo.phone.isBlank() || UserOrderInfo.address.isBlank()) {
            errorToast("收货信息不能为空")
            return
        }
        if (radioGroup.checkedRadioButtonId == -1) {
            errorToast("请选择配送方式")
            return
        }
        cartViewModel.postOrder(UserOrderInfo).observe(this, Observer {
            successToast("订单提交成功")
            Settings.needReloadCart = true
            finish()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ARQC_ORDER_ADDRESS && resultCode == Activity.RESULT_OK){
            data?.getParcelableExtra<AddressInfo>(Constant.KEY_ADDRESS_INFO)?.let {
                nameText.text = it.userName
                phoneText.text = it.phone
                addressText.text = it.address
                UserOrderInfo.name = it.userName
                UserOrderInfo.phone = it.phone
                UserOrderInfo.address = it.address
                UserOrderInfo.addressId = it.id
            }
        }
    }


}