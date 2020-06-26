package com.bingo.ybd.modules.main.activity

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.config.Settings
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.data.model.MedDetail
import com.bingo.ybd.data.model.MedTypeMaps
import com.bingo.ybd.ext.successToast
import com.bingo.ybd.modules.main.vm.MainViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_med_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MedDetailActivity:BaseVMActivity() {

    var medId = 0

    private val mainViewModel: MainViewModel by viewModel()

    override fun getViewModel(): BaseViewModel = mainViewModel

    override fun getLayoutId(): Int = R.layout.activity_med_detail

    override fun initView() {
        addToCartText.setOnClickListener {
            mainViewModel.addMedToCart(Settings.Account.userId.toInt(), medId)
                .observe(this, Observer {
                    Settings.needReloadCart = true
                    successToast("成功加入购物车")
                })
        }

        buyText.setOnClickListener {
            mainViewModel.addMedToOrderAndGetOrderInfo(Settings.Account.userId.toInt(), medId)
                .observe(this, Observer {
                    Settings.needReloadCart = true
                    successToast(it.data.toString())
                })
        }

        cartImg.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(Constant.KEY_CART_PAGE_INDEX, 2)
            intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    override fun initData() {
        medId = intent.getIntExtra(Constant.KEY_MED_ID, 0)
        mainViewModel.getMedDetail(medId).observe(this, Observer {
            it.data.apply {
                Glide.with(this@MedDetailActivity).load(pic).into(medImg)
                prescriptText.text = if (prescript == 1) "处方" else "非处方"
                medNameText.text = name
                medPriceText.text = "￥$price"
                medTypeText.text = "药品类型:$typeName"
                medSalesNumText.text = "销量:$salesNum"
                normalNameText.text = normalName
                goodsNameText.text = goodsName
                compositionText.text = composition
                avoidText.text = avoid
                functionText.text = function
                usageText.text = usage
                propertiesText.text = properties
                packingSizeText.text = packingSize
                adverseReactionText.text = adverseReaction
                storeConditionText.text = storeCondition
                validTimeText.text = validTime
                attentionsText.text = attentions
                registerNumberText.text = registerNumber
                manufacturerText.text = manufacturer
            }
        })

    }


}