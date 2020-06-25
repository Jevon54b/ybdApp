package com.bingo.ybd.modules.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.data.model.BaseResponse
import com.bingo.ybd.data.model.MedBrief
import com.bingo.ybd.data.model.UserInfo
import com.bingo.ybd.data.repository.Repository

class MainViewModel(val repository: Repository) : BaseViewModel() {

    val mUserInfo = MutableLiveData<UserInfo>()

    fun saveUserInfo(userInfo: UserInfo) {
        mUserInfo.postValue(userInfo)
    }

    fun getTop5MedList(): LiveData<BaseResponse<List<MedBrief>>> = emit {
        repository.getTop5MedList()
    }


}