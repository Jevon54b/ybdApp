package com.bingo.ybd.modules.user.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.data.repository.Repository
import com.bingo.ybd.modules.user.model.UserInfoModel

class LoginViewModel(val repository:Repository):BaseViewModel(){
    var mUserInfoModel = MutableLiveData<UserInfoModel>()

    fun userLogin(phone:String,password:String) {
        launch {
            Log.d("test","test")
            repository.userLogin(phone,password)
        }
    }

}