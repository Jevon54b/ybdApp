package com.bingo.ybd.modules.user.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(val status:String,
                         val msg:String,
                         @SerializedName("data")val userInfoModel: UserInfoModel)

data class UserInfoModel(val id:String = "",
                     val name:String = "",
                     val password:String = "",
                     val phone:String = "",
                     val address:String = "",
                     val nick:String = "",
                     val money:String = "")