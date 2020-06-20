package com.bingo.ybd.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(val status:String,
                         val msg:String,
                         @SerializedName("data") val userInfoModel: UserInfo
)

data class UserInfo(
    val id: String = "",
                     val name:String = "",
                     val password:String = "",
                     val phone:String = "",
                     val address:String = "",
                     val nick:String = "",
                     val money:String = "")