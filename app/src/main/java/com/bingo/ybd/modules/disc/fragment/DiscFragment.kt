package com.bingo.ybd.modules.disc.fragment


import androidx.recyclerview.widget.LinearLayoutManager
import com.bingo.ybd.R
import com.bingo.ybd.base.fragment.BaseVMFragment
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.modules.disc.custom.ArticleAdapter
import com.bingo.ybd.modules.disc.model.ArticleModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_disc.*

class DiscFragment: BaseVMFragment() {

    lateinit var articleList:List<ArticleModel>

    override fun getLayoutRes(): Int {
        return R.layout.fragment_disc
    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {
        testData()
        var adapter = ArticleAdapter(requireContext(),articleList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    fun testData(){
        var json = " [{\n" +
                "            \"id\": \"4\",\n" +
                "            \"title\": \"“胃！你好吗？”\",\n" +
                "            \"author\": \"詹晶晶中医生\",\n" +
                "            \"realease_time\": 1577504586000,\n" +
                "            \"content\": null,\n" +
                "            \"pic\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577514687028&di=109989f3628a49a6328d6dd6542c6927&imgtype=0&src=http%3A%2F%2Fwww.51yuansu.com%2Fpic2%2Fcover%2F00%2F29%2F41%2F580c18aab97d1_610.jpg\",\n" +
                "            \"read_num\": 42,\n" +
                "            \"comment_num\": 11\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"3\",\n" +
                "            \"title\": \"浅谈颈椎病\",\n" +
                "            \"author\": \"叶林医生\",\n" +
                "            \"realease_time\": 1577504536000,\n" +
                "            \"content\": null,\n" +
                "            \"pic\": \"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1995674776,4205035503&fm=26&gp=0.jpg\",\n" +
                "            \"read_num\": 11,\n" +
                "            \"comment_num\": 6\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"2\",\n" +
                "            \"title\": \"补肾保健汤水\",\n" +
                "            \"author\": \"陈医生\",\n" +
                "            \"realease_time\": 1577504440000,\n" +
                "            \"content\": null,\n" +
                "            \"pic\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577514508150&di=51d8510a826663619d39e07385a14286&imgtype=0&src=http%3A%2F%2Fimg000.hc360.cn%2Fk1%2FM05%2FEE%2F8D%2F04b61dF5E601b48F1594d02F2F26399EFA.jpg\",\n" +
                "            \"read_num\": 10,\n" +
                "            \"comment_num\": 4\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"1\",\n" +
                "            \"title\": \"驱寒保暖喝什么好\",\n" +
                "            \"author\": \"alex\",\n" +
                "            \"realease_time\": 1577422373000,\n" +
                "            \"content\": null,\n" +
                "            \"pic\": \"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4014775673,1424225092&fm=26&gp=0.jpg\",\n" +
                "            \"read_num\": 18,\n" +
                "            \"comment_num\": 2\n" +
                "        }]"
        var gson = Gson()
        articleList = gson.fromJson<List<ArticleModel>>(json,object:TypeToken<List<ArticleModel>>(){}.type)
    }
}