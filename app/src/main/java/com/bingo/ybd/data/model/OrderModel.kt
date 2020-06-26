package com.bingo.ybd.data.model

import com.google.gson.annotations.SerializedName

data class Order(
    val id: String,
    @SerializedName("total_sum") val totalSum: Float,
    @SerializedName("create_time") val createTime: String,
    @SerializedName("pay_time") val payTime: String,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("distributer_id") val distributorId: Int,
    val state: Int,
    @SerializedName("user_name") val userName: String,
    @SerializedName("user_phone") val userPhone: String,
    @SerializedName("user_address") val userAddress: String,
    @SerializedName("store_address") val storeAddress: String,
    val speed: Int,
    @SerializedName("need_check") val needCheck: Int,
    val medList: List<MedInOrder>
)

data class PostOrderInfo(
    val name: String,
    val phone: String,
    val address: String,
    val speed: Int,
    val orderTotalSum: Float
)