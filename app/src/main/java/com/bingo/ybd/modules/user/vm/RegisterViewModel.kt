package com.bingo.ybd.modules.user.vm

import androidx.lifecycle.LiveData
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.data.model.BaseResponse
import com.bingo.ybd.data.model.UserInfo
import com.bingo.ybd.data.repository.Repository

class RegisterViewModel(private val repository: Repository) : BaseViewModel() {
    companion object {
        const val TAG = "RegisterViewModel"
    }

    fun userRegister(nick: String, phone: String, password: String): LiveData<BaseResponse<Any>> =
        emit {
            repository.userRegiser(nick, phone, password)
        }
}