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

    fun userLogin(phone: String, password: String): LiveData<BaseResponse<UserInfo>> = emit {
        repository.userLogin(phone, password)
    }

}