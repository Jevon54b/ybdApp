package com.bingo.ybd.modules.mine.vm

import android.location.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.config.Settings
import com.bingo.ybd.data.model.AddressInfo
import com.bingo.ybd.data.model.BaseResponse

import com.bingo.ybd.data.model.Order
import com.bingo.ybd.data.repository.Repository

class MineViewModel(): BaseViewModel(){

    val startingOrderListData = MutableLiveData<List<Order>>()

    val finishedOrderListData = MutableLiveData<List<Order>>()

    val addressInfoList = MutableLiveData<List<AddressInfo>>()


    fun getStartingOrderList(){
        launch {
            val response = Repository.getStartingOrderList(Settings.Account.userId.toInt())
            startingOrderListData.value = response.data
            response
        }
    }

    fun getFinishedOrderListData(){
        launch {
            val response = Repository.getFinishedOrderList(Settings.Account.userId.toInt())
            finishedOrderListData.value = response.data
            response
        }
    }

    fun getAddressInfoList(){
        launch {
            val response = Repository.getAddressInfoList(Settings.Account.userId)
            addressInfoList.value = response.data
            response
        }
    }

    fun getLastUseAddressInfo(): LiveData<BaseResponse<AddressInfo>> = emit{
        Repository.getLastUseAddressInfo(Settings.Account.userId.toString())
    }


    fun createAddressInfo(address: String, phone: String, userName: String, latitude: Double, longitude: Double):LiveData<BaseResponse<Any>> = emit {
        Repository.createAddressInfo(Settings.Account.userId.toInt(),address,phone, userName, latitude, longitude)
    }

    fun updateAddressInfo(id: Int,address: String, phone: String, userName: String, latitude: Double, longitude: Double):LiveData<BaseResponse<Any>> = emit {
        Repository.updateAddressInfo(id,address,phone, userName, latitude, longitude)
    }
}