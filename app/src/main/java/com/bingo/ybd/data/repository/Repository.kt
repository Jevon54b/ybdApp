package com.bingo.ybd.data.repository

import com.bingo.ybd.R
import com.bingo.ybd.data.model.BaseResponse
import com.bingo.ybd.data.model.UserInfo
import com.bingo.ybd.data.network.ApiService
import com.bingo.ybd.data.network.ServiceCreator

object Repository {

    private val apiService = ServiceCreator.create<ApiService>()


    suspend fun userLogin(phone: String, password: String): BaseResponse<UserInfo> {
        return apiService.login(phone, password)
    }


    suspend fun userRegiser(nick: String, phone: String, password: String): BaseResponse<Any> {
        return apiService.register(nick, phone, password)
    }


    fun getUserPhoto(): Int {
        var photoList = listOf(
            R.mipmap.p0,
            R.mipmap.p1,
            R.mipmap.p2,
            R.mipmap.p3,
            R.mipmap.p4,
            R.mipmap.p5,
            R.mipmap.p6)
        var randomIndex = (0..6).random()
        return photoList[randomIndex]
    }

}