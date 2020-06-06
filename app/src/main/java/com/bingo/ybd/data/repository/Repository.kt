package com.bingo.ybd.data.repository

import android.util.Log
import com.bingo.ybd.R
import com.bingo.ybd.data.network.BingoNetwork
import com.bingo.ybd.modules.user.model.LoginResponse

object Repository{
    suspend fun userLogin(phone:String,password:String):LoginResponse {
        Log.d("test","test2")
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