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


data class Article(
    val id: String,
    val title: String,
    val author: String,
    @SerializedName("realease_time") val releaseTime: Timestamp,
    val content: String,
    val pic: String,
    @SerializedName("read_num") val readNum: Int,
    @SerializedName("comment_num") val commentNum: Int
)