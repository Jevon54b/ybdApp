package com.bingo.ybd.modules.mine.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.bingo.ybd.R
import com.bingo.ybd.base.fragment.BaseVMFragment
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.data.model.Order
import com.bingo.ybd.modules.mine.custom.OrderAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_current_order.*

class OldOrderFragment:BaseVMFragment() {
    lateinit var orderList: List<Order>

    override fun getLayoutRes(): Int {
        return R.layout.fragment_old_order
    }

    fun initTestData(){
        var json = "[\n" +
                "        {\n" +
                "            \"id\": 19,\n" +
                "            \"total_sum\": 59.0,\n" +
                "            \"create_time\": null,\n" +
                "            \"pay_time\": \"2019-12-29 13:13:27\",\n" +
                "            \"user_id\": 0,\n" +
                "            \"distributer_id\": 0,\n" +
                "            \"state\": 1,\n" +
                "            \"user_name\": null,\n" +
                "            \"user_phone\": null,\n" +
                "            \"user_address\": null,\n" +
                "            \"store_address\": null,\n" +
                "            \"speed\": 0,\n" +
                "            \"need_check\": 0,\n" +
                "            \"medList\": [\n" +
                "                {\n" +
                "                    \"med_id\": 20029,\n" +
                "                    \"om_id\": 42,\n" +
                "                    \"name\": \"[健途]甜橙味多种维生素泡腾片\",\n" +
                "                    \"pic\": \"https://imgq.ddky.com/c/product/533839/big/z_1.jpg?t=1517896916255&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F800x800%21%2Fquality%2F100\",\n" +
                "                    \"prescript\": 0,\n" +
                "                    \"packing_size\": \"详见说明书。\",\n" +
                "                    \"med_num\": 1,\n" +
                "                    \"price\": 35.0,\n" +
                "                    \"total_sum\": 59.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"med_id\": 20008,\n" +
                "                    \"om_id\": 43,\n" +
                "                    \"name\": \"[鱼峰山]跌打万花油\",\n" +
                "                    \"pic\": \"https://imgq.ddky.com/c/product/544258/big/z_1.jpg?t=1559626518766&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F800x800%21%2Fquality%2F100\",\n" +
                "                    \"prescript\": 0,\n" +
                "                    \"packing_size\": \"详见说明书。\",\n" +
                "                    \"med_num\": 1,\n" +
                "                    \"price\": 20.0,\n" +
                "                    \"total_sum\": 59.0\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]"
        var gson = Gson()
        orderList = gson.fromJson(json, object : TypeToken<List<Order>>() {}.type)
    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {
        initTestData()
        val orderAdapter = OrderAdapter(requireContext())
        orderRecyclerView.adapter = orderAdapter
        orderRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}