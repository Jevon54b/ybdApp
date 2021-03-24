package com.bingo.ybd.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Message(
    @SerializedName("name")val name:String = "",
    @SerializedName("content")val content:String = "",
    @SerializedName("type")val type:Int = -1,
    @SerializedName("time")val time:String = "")
// 0: from_user , 1: from_support


// code  0:无客服在线 , 1:空闲客服  -1:客服忙线
data class SupportInfo(
    val code:Int = 0,
    val supportId: String = ""
)


@Parcelize
data class AddressInfo(
    @SerializedName("id")val id : Int = 0,
    @SerializedName("user_id")val userId : Int = 0,
    @SerializedName("address")val address : String = "",
    @SerializedName("phone")val phone : String = "",
    @SerializedName("user_name")val userName : String = "",
    @SerializedName("latitude")val latitude: Double = 0.0,
    @SerializedName("longitude")val longitude: Double = 0.0
): Parcelable

@Parcelize
data class PointInfo(
    val poiName : String,
    val poiAddress : String,
    val latitude: Double,
    val longitude : Double
) : Parcelable