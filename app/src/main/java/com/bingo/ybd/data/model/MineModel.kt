package com.bingo.ybd.data.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("name")val name:String = "",
    @SerializedName("content")val content:String = "",
    @SerializedName("type")val type:Int = 0,
    @SerializedName("time")val time:String = "")
// 0: from_user , 1: from_support


// code  0:无客服在线 , 1:空闲客服  -1:客服忙线
data class SupportInfo(
    val code:Int = 0,
    val supportId: Int = 0
)