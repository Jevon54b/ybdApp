package com.bingo.ybd.data.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class Comment(
    val id: String,
    @SerializedName("article_id") val articleId: String,
    val commenter: String,
    val content: String,
    @SerializedName("realease_time") val releaseTime: Timestamp,
    @SerializedName("commenter_id") val commenterId: String
)

/*
* \"id\": \"1\",\n" +
                "            \"title\": \"驱寒保暖喝什么好\",\n" +
                "            \"author\": \"alex\",\n" +
                "            \"realease_time\": 1577422373000,\n" +
                "            \"content\": null,\n" +
                "            \"pic\": \"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4014775673,1424225092&fm=26&gp=0.jpg\",\n" +
                "            \"read_num\": 18,\n" +
                "            \"comment_num\": 2\n" +
 */

data class Article(
    val id: String,
    val title: String,
    val author: String,
    @SerializedName("realease_time") val releaseTime: String,
    val content: String,
    val pic: String,
    @SerializedName("read_num") val readNum: Int,
    @SerializedName("comment_num") val commentNum: Int
)