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

    val TAG = "MedListActivity"

    val DEFAULT_SORT_INDEX = 0
    val SALES_SORT_INDEX = 1
    val PRICE_UP_SORT_INDEX = 2
    val PRICE_DOWN_SORT_INDEX = 3
    var currentIndex = DEFAULT_SORT_INDEX
    var NORMAL_COLOR = 0
    var SELECTED_COLOR = 0
    lateinit var medList: List<MedBrief>

    override fun getLayoutId(): Int {
        return R.layout.activity_med_list
    }

    override fun initView() {
        initTestData()
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

    private fun initTestData() {
        var gson = Gson()
        var json = "[\n" +
                "        {\n" +
                "            \"id\": 20004,\n" +
                "            \"name\": \"[中族]板蓝根颗粒\",\n" +
                "            \"price\": 15.0,\n" +
                "            \"prescript\": 0,\n" +
                "            \"note\": \"功能主治：清热解毒，凉血利咽。用于肺胃热盛所致的咽喉肿痛、口咽干燥；急性扁桃体炎见上述证候者。\",\n" +
                "            \"pic\": \"https://imgq.ddky.com/c/product/545155/big/z_1.jpg?t=1563173704678&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F800x800%21%2Fquality%2F100\",\n" +
                "            \"salesnum\": 10,\n" +
                "            \"med_type\": 30001\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 20007,\n" +
                "            \"name\": \"[葵花]小儿肺热咳喘颗粒\",\n" +
                "            \"price\": 24.8,\n" +
                "            \"prescript\": 0,\n" +
                "            \"note\": \"功能主治：清热解毒，宣肺止咳，化痰平喘。用于感冒，支气管炎属痰热壅肺证者。\",\n" +
                "            \"pic\": \"https://imgq.ddky.com/c/product/535177/big/z_1.jpg?t=1546399098890&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F800x800%21%2Fquality%2F100\",\n" +
                "            \"salesnum\": 8,\n" +
                "            \"med_type\": 30007\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 20009,\n" +
                "            \"name\": \"云南白药膏\",\n" +
                "            \"price\": 15.0,\n" +
                "            \"prescript\": 0,\n" +
                "            \"note\": \"功能主治：活血散瘀，消肿止痛，祛风除湿。用于跌打损伤，瘀血肿痛，风湿疼痛等症。\",\n" +
                "            \"pic\": \"https://imgq.ddky.com/c/product/521507/big/z_1.jpg?t=999999&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F800x800%21%2Fquality%2F100\",\n" +
                "            \"salesnum\": 2,\n" +
                "            \"med_type\": 30006\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 20013,\n" +
                "            \"name\": \"[前列康]普乐安片\",\n" +
                "            \"price\": 32.8,\n" +
                "            \"prescript\": 0,\n" +
                "            \"note\": \"功能主治：补肾固本。用于肾气不固所致的腰膝酸软，尿后余沥。\",\n" +
                "            \"pic\": \"https://imgq.ddky.com/c/product/546955/big/z_1.jpg?t=1574307063822&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F800x800%21%2Fquality%2F100\",\n" +
                "            \"salesnum\": 2,\n" +
                "            \"med_type\": 30002\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 20016,\n" +
                "            \"name\": \"[江中]复方草珊瑚含片\",\n" +
                "            \"price\": 20.0,\n" +
                "            \"prescript\": 0,\n" +
                "            \"note\": \"功能主治：疏风清热，消肿止痛，清利咽喉。用于外感风热所致的喉痹，症见咽喉肿痛、声哑失音；急性咽喉炎见上述证候者。\",\n" +
                "            \"pic\": \"https://imgq.ddky.com/c/product/551729/big/z_1.jpg?t=1573119724792&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F800x800%21%2Fquality%2F100\",\n" +
                "            \"salesnum\": 1,\n" +
                "            \"med_type\": 30003\n" +
                "        }\n" +
                "    ]"
        medList = gson.fromJson(json, object : TypeToken<List<MedBrief>>() {}.type)
    }

    override fun onClick(v: View?) {
        Log.d(TAG,"onClick:${v!!.id}")
        when(v!!.id){
            R.id.defaultIndex -> if(currentIndex!=DEFAULT_SORT_INDEX) switchIndex(DEFAULT_SORT_INDEX)
            R.id.salesNumIndex -> if (currentIndex!=SALES_SORT_INDEX) switchIndex(SALES_SORT_INDEX)
            R.id.priceIndex -> switchIndex(if(currentIndex == PRICE_UP_SORT_INDEX)  PRICE_DOWN_SORT_INDEX else PRICE_UP_SORT_INDEX )
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