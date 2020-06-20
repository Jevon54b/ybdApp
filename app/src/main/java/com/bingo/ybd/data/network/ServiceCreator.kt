package com.bingo.ybd.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "http://245z3l3522.qicp.vip/YBD/"
    private val retrofit: Retrofit

    init {
        val logger = HttpLoggingInterceptor()

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    fun <T> create(serviceClass:Class<T>):T = retrofit.create(serviceClass)

    inline fun <reified  T> create():T = create(T::class.java)

}