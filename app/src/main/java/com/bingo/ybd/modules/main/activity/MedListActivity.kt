package com.bingo.ybd.modules.main.activity

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.data.model.MedBrief
import com.bingo.ybd.modules.main.custom.MedShowAdpter
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_article_detail.*
import kotlinx.android.synthetic.main.activity_med_list.*
import kotlinx.android.synthetic.main.activity_med_list.recyclerView

class MedListActivity:BaseVMActivity(),View.OnClickListener {

    companion object {
        const val TAG = "MedListActivity"
        const val DEFAULT_SORT_INDEX = 0
        const val SALES_SORT_INDEX = 1
        const val PRICE_UP_SORT_INDEX = 2
        const val PRICE_DOWN_SORT_INDEX = 3
    }

    var currentIndex = DEFAULT_SORT_INDEX
    var NORMAL_COLOR = 0
    var SELECTED_COLOR = 0
    lateinit var medList: List<MedBrief>

    override fun getLayoutId(): Int {
        return R.layout.activity_med_list
    }

    override fun initView() {
        NORMAL_COLOR = getColor(R.color.colorWordBlack)
        SELECTED_COLOR = getColor(R.color.colorPrimaryDark)
        defaultIndex.setOnClickListener(this)
        salesNumIndex.setOnClickListener(this)
        priceIndex.setOnClickListener(this)
        defaultIndex.setTextColor(SELECTED_COLOR)
        currentIndex = DEFAULT_SORT_INDEX

        var medAdapter = MedShowAdpter(this)
        recyclerView.adapter = medAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        this.dividerBuilder()
            .showLastDivider()
            .build()
            .addTo(recyclerView)
    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun onClick(v: View?) {
        Log.d(TAG,"onClick:${v!!.id}")
        when (v!!.id) {
            R.id.defaultIndex ->
                if (currentIndex != DEFAULT_SORT_INDEX) switchIndex(DEFAULT_SORT_INDEX)
            R.id.salesNumIndex ->
                if (currentIndex != SALES_SORT_INDEX) switchIndex(SALES_SORT_INDEX)
            R.id.priceIndex -> {
                switchIndex(
                    if (currentIndex == PRICE_UP_SORT_INDEX)
                        PRICE_DOWN_SORT_INDEX
                    else
                        PRICE_UP_SORT_INDEX
                )
            }
            else -> return
        }
    }

    fun switchIndex(index:Int){
        when(index){
            DEFAULT_SORT_INDEX ->{
                currentIndex = DEFAULT_SORT_INDEX
                clearSelectedColor()
                defaultIndex.setTextColor(SELECTED_COLOR)
            }
            SALES_SORT_INDEX ->{
                currentIndex = SALES_SORT_INDEX
                clearSelectedColor()
                salesNumIndex.setTextColor(SELECTED_COLOR)
            }
            PRICE_UP_SORT_INDEX ->{
                currentIndex = PRICE_UP_SORT_INDEX
                clearSelectedColor()
                priceIndexText.setTextColor(SELECTED_COLOR)
                upIndex.isSelected = true
            }
            PRICE_DOWN_SORT_INDEX ->{
                currentIndex = PRICE_DOWN_SORT_INDEX
                clearSelectedColor()
                priceIndexText.setTextColor(SELECTED_COLOR)
                downIndex.isSelected = true
            }
            else -> return
        }
    }

    private fun clearSelectedColor() {
        defaultIndex.setTextColor(NORMAL_COLOR)
        salesNumIndex.setTextColor(NORMAL_COLOR)
        priceIndexText.setTextColor(NORMAL_COLOR)
        upIndex.isSelected = false
        downIndex.isSelected = false
    }
}