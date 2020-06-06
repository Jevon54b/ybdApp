package com.bingo.ybd.modules.mine.model

import com.bingo.ybd.modules.main.model.MedBriefModel
import com.google.gson.annotations.SerializedName

data class OrderModel(
    val id:Int,
    @SerializedName("total_sum")val totalSum:Float,
    @SerializedName("pay_time")val payTime:String,
    val state:Int,
    val speed:Int,
    @SerializedName("need_check")val needCheck:Int,
    val medList:List<MedBriefModel>
)

data class OrderListResponse(
    val status:String,
    val msg:String,
    @SerializedName("data")val orderList:List<OrderModel>
)