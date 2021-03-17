package com.bingo.ybd.modules.main.activity

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.modules.main.custom.MedShowAdapter
import com.bingo.ybd.modules.main.vm.MainViewModel
import com.fondesa.recyclerviewdivider.dividerBuilder
import kotlinx.android.synthetic.main.activity_med_list.*
import kotlinx.android.synthetic.main.activity_med_list.recyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MedListActivity : BaseVMActivity(), View.OnClickListener {

    companion object {
        const val TAG = "MedListActivity"
        const val DEFAULT_SORT_INDEX = 0
        const val SALES_SORT_INDEX = 1
        const val PRICE_UP_SORT_INDEX = 2
        const val PRICE_DOWN_SORT_INDEX = 3
    }

    override fun getLayoutId(): Int = R.layout.activity_med_list

    override fun getViewModel(): BaseViewModel = mainViewModel

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var medShowAdapter: MedShowAdapter

    private var currentIndex = DEFAULT_SORT_INDEX
    private var NORMAL_COLOR = 0
    private var SELECTED_COLOR = 0

    private var listCode = Constant.VALUE_TYPE_LIST
    private var typeId: Int = 0
    private var searchKeyWord = ""


    override fun initView() {
        NORMAL_COLOR = getColor(R.color.colorWordBlack) //灰色
        SELECTED_COLOR = getColor(R.color.colorPrimaryDark) //主题色
        defaultIndex.setOnClickListener(this)
        salesNumIndex.setOnClickListener(this)
        priceIndex.setOnClickListener(this)
        defaultIndex.setTextColor(SELECTED_COLOR)
        currentIndex = DEFAULT_SORT_INDEX

        medShowAdapter = MedShowAdapter(this)
        recyclerView.adapter = medShowAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        this.dividerBuilder()
            .showLastDivider()
            .build()
            .addTo(recyclerView)
    }

    override fun initData() {
        mainViewModel.medListData.observe(this, Observer {
            medShowAdapter.replaceAll(it)
            medShowAdapter.notifyDataSetChanged()
        })

        listCode = intent.getIntExtra(Constant.KEY_MED_LIST_CODE, 0)
        if (listCode == Constant.VALUE_TYPE_LIST) {
            typeId = intent.getIntExtra(Constant.KEY_TYPE_ID, 0)
            mainViewModel.getMedListByTypeId(typeId, currentIndex)
        } else {
            searchKeyWord = intent.getStringExtra(Constant.KEY_SEARCH_WORD)
            mainViewModel.searchMed(searchKeyWord,currentIndex)
        }

    }

    override fun onClick(v: View?) {
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

    fun switchIndex(index: Int) {
        when (index) {
            DEFAULT_SORT_INDEX -> {
                currentIndex = DEFAULT_SORT_INDEX
                clearSelectedColor()
                defaultIndex.setTextColor(SELECTED_COLOR)

            }
            SALES_SORT_INDEX -> {
                currentIndex = SALES_SORT_INDEX
                clearSelectedColor()
                salesNumIndex.setTextColor(SELECTED_COLOR)
            }
            PRICE_UP_SORT_INDEX -> {
                currentIndex = PRICE_UP_SORT_INDEX
                clearSelectedColor()
                priceIndexText.setTextColor(SELECTED_COLOR)
                upIndex.isSelected = true
            }
            PRICE_DOWN_SORT_INDEX -> {
                currentIndex = PRICE_DOWN_SORT_INDEX
                clearSelectedColor()
                priceIndexText.setTextColor(SELECTED_COLOR)
                downIndex.isSelected = true
            }
            else -> {
            }
        }
        if (listCode == Constant.VALUE_TYPE_LIST) {
            mainViewModel.getMedListByTypeId(typeId, currentIndex)
        } else {
            mainViewModel.searchMed(searchKeyWord,currentIndex)
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