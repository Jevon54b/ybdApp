package com.bingo.ybd.modules.disc.model

import com.google.gson.annotations.SerializedName

data class CommentModel(
    val id:String,
    @SerializedName("article_id")val articleId:String,
    @SerializedName("commenter")val commenterName:String,
    val content:String,
    @SerializedName("realease_time")val releaseTime:String,
    @SerializedName("commenter_id")val commenterId:String)