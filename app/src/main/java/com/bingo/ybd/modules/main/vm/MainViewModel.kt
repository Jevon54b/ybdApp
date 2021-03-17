package com.bingo.ybd.modules.main.vm

import android.provider.Settings
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.data.model.*
import com.bingo.ybd.data.repository.Repository

class MainViewModel(val repository: Repository) : BaseViewModel() {

    val mUserInfo = MutableLiveData<UserInfo>()
    val medListData = MutableLiveData<List<MedBrief>>()

    companion object {
        const val TAG = "MainViewModel"
    }

    fun saveUserInfo(userInfo: UserInfo) {
        mUserInfo.postValue(userInfo)
    }

    fun getTop5MedList(): LiveData<BaseResponse<List<MedBrief>>> = emit {
        repository.getTop5MedList()
    }

    fun getMedDetail(medId: Int): LiveData<BaseResponse<MedDetail>> = emit {
        repository.getMedDetail(medId)
    }

    fun addMedToCart(userId: Int, medId: Int): LiveData<BaseResponse<Any>> = emit {
        repository.addMedToCurOrder(userId, medId)
    }

    fun addMedToOrderAndGetOrderInfo(userId: Int, medId: Int)
            : LiveData<BaseResponse<List<MedInOrder>>> = emit {
        repository.addMedToCurOrder(userId, medId)
        repository.getMedCartList(userId)
    }

    fun getMedListByTypeId(typeId: Int,sortFlag: Int){
        launch {
            val response = repository.getMedListByTypeId(typeId,sortFlag)
            medListData.value = response.data
            response
        }
    }

    fun searchMed(keyword: String, sortFlag: Int){
        launch {
            val response = repository.searchMed(keyword,sortFlag)
            medListData.value = response.data
            response
        }
    }


}