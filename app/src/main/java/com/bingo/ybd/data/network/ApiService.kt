package com.bingo.ybd.data.network

import com.bingo.ybd.modules.user.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
//    @GET("v2/place?token=${BingoWeatherApplication.token}&lang=zh_CN")
//    fun searchPlaces(@Query("query") query:String): Call<PlaceResponse>

    @FormUrlEncoded
    @POST("user/login.do")
    fun login(@Field("phone")phone:String,@Field("password")password:String):Call<LoginResponse>

}