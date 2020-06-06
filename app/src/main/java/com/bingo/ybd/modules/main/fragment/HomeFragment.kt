package com.bingo.ybd.modules.main.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.bingo.ybd.R
import com.bingo.ybd.base.fragment.BaseVMFragment
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.modules.main.custom.ImageBannerAdapter
import com.bingo.ybd.modules.main.custom.MedShowAdpter
import com.bingo.ybd.modules.main.model.MedBriefModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment:BaseVMFragment() {

    lateinit var medList: List<MedBriefModel>

    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {
        initTestData()
        initBanner()
        initRecyclerView()
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
        medList = gson.fromJson<List<MedBriefModel>>(json,object:TypeToken<List<MedBriefModel>>(){}.type)
    }

    private fun initRecyclerView(){
        recyclerView.adapter = MedShowAdpter(medList,requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initBanner() {
        banner.adapter = ImageBannerAdapter(requireContext(),medList)
        banner.indicator = CircleIndicator(requireContext())
        banner.start()
    }

    override fun onStop() {
        super.onStop()
        banner.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        banner.destroy()
    }
}