package com.bingo.ybd.modules.user.vm

import android.util.Log
import androidx.lifecycle.LiveData
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.data.model.BaseResponse
import com.bingo.ybd.data.model.UserInfo
import com.bingo.ybd.data.repository.Repository

class LoginViewModel(private val repository: Repository) : BaseViewModel() {

    companion object {
        const val TAG = "LoginViewModel"
    }

//    private val datas : MutableLiveData<LoginResponse> by lazy {
//        MutableLiveData<LoginResponse>().also {  }
//    }

//    fun userLogin(phone:String,password:String){
//        launch {
//            Log.e(TAG,"test2")
//            repository.userLogin(phone, password)
//        }
//    }

    //    var mUserInfoModel = MutableLiveData<UserInfo>()
//
    fun userLogin(phone: String, password: String): LiveData<BaseResponse<UserInfo>> = emit {
        Log.e(TAG, "test2")
        repository.userLogin(phone, password)
    }

}