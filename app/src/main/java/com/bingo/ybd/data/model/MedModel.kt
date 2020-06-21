package com.bingo.ybd.data.model

import com.google.gson.annotations.SerializedName

data class MedBrief(
    val id: Int,
    val name: String,
    val price: Float,
    val prescript: Int,
    val note: String,
    val pic: String,
    @SerializedName("salesnum") val salesNum: Int,
    @SerializedName("med_type") val medType: Int
)


data class MedDetail(
    val id: Int,
    val name: String,
    val price: Float,
    val prescript: Int,
    val note: String,
    val pic: String,
    @SerializedName("salesnum") val salesNum: Int,
    @SerializedName("med_type") val medType: Int,
    @SerializedName("type_name") val typeName: String,
    @SerializedName("normal_name") val normalName: String,
    @SerializedName("goods_name") val goodsName: String,
    val composition: String,
    val avoid: String,
    val function: String,
    val usage: String,
    val properties: String,
    @SerializedName("packing_size") val packingSize: String,
    @SerializedName("adverse_reaction") val adverseReaction: String,
    @SerializedName("store_condition") val storeCondition: String,
    @SerializedName("valid_time") val validTime: String,
    val attentions: String,
    @SerializedName("med_type") val registerNumber: String,
    val manufacturer: String
)

data class MedInOrder(
    @SerializedName("med_id") val medId: Int,
    @SerializedName("om_id") val mOrderId: Int,
    val name: String,
    val pic: String,
    val prescript: Int,
    @SerializedName("packing_size") val packingSize: String,
    @SerializedName("med_num") val medNum: String,
    val price: Float,
    @SerializedName("total_sum") val totalSum: Float
)