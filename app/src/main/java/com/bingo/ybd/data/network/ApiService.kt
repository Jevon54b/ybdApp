package com.bingo.ybd.data.network

import com.bingo.ybd.data.model.BaseResponse
import com.bingo.ybd.data.model.UserInfo
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
//    @GET("v2/place?token=${BingoWeatherApplication.token}&lang=zh_CN")
//    fun searchPlaces(@Query("query") query:String): Call<PlaceResponse>

    @FormUrlEncoded
    @POST("user/login.do")
    suspend fun login(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): BaseResponse<UserInfo>


    @FormUrlEncoded
    @POST("user/register.do")
    suspend fun register(
        @Field("nick") nick: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ): BaseResponse<Any>

    @GET("medicine/getTop5MedList.do")
    suspend fun getTop5MedList()


}