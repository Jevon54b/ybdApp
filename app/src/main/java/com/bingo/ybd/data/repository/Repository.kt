package com.bingo.ybd.data.repository

import com.bingo.ybd.R
import com.bingo.ybd.data.model.BaseResponse
import com.bingo.ybd.data.model.UserInfo
import com.bingo.ybd.data.network.BingoNetwork

object Repository{
    suspend fun userLogin(phone: String, password: String): BaseResponse<UserInfo> {
        return BingoNetwork.login(phone, password)
    }



    fun getUserPhoto():Int{
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