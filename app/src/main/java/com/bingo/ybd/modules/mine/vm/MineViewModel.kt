package com.bingo.ybd.modules.mine.vm

import androidx.lifecycle.MutableLiveData
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.config.Settings

import com.bingo.ybd.data.model.Order
import com.bingo.ybd.data.repository.Repository

class MineViewModel(): BaseViewModel(){

    val startingOrderListData = MutableLiveData<List<Order>>()

    val finishedOrderListData = MutableLiveData<List<Order>>()


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
}