package com.bingo.ybd.modules.disc.model

import com.google.gson.annotations.SerializedName

data class ArticleListResponse(val status:String,
                               val msg:String,
                               @SerializedName("data")val articleList:List<ArticleModel>)
/*"id": "4",
            "title": "“胃！你好吗？”",
            "author": "詹晶晶中医生",
            "realease_time": 1577504586000,
            "content": null,
            "pic": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577514687028&di=109989f3628a49a6328d6dd6542c6927&imgtype=0&src=http%3A%2F%2Fwww.51yuansu.com%2Fpic2%2Fcover%2F00%2F29%2F41%2F580c18aab97d1_610.jpg",
            "read_num": 42,
            "comment_num": 11*/
data class ArticleModel(val id:String,
                        val title:String,
                        val author:String,
                        @SerializedName("realease_time")val releaseTime:String,
                        val content:String,
                        val pic:String,
                        @SerializedName("read_num")val readNum:Int,
                        @SerializedName("comment_num")val commentNum:Int)