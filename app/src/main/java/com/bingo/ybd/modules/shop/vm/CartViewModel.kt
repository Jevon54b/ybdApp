package com.bingo.ybd.modules.shop.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.config.Settings
import com.bingo.ybd.data.model.BaseResponse
import com.bingo.ybd.data.model.MedInOrder
import com.bingo.ybd.data.model.UserOrderInfo
import com.bingo.ybd.data.repository.Repository

class CartViewModel(private val repository: Repository) : BaseViewModel() {

    val cartLiveData: MutableLiveData<List<MedInOrder>> = MutableLiveData()

    fun getMedListInCart(userId: Int) {
        launch {
            val response = repository.getMedCartList(userId)
            cartLiveData.value = response.data
            return@launch response
        }
    }

    fun addMedCountAndGetOrderInfo(userId: Int, medId: Int) {
        launch {
            repository.addMedToCurOrder(userId, medId)
            val response = repository.getMedCartList(userId)
            cartLiveData.value = response.data
            return@launch response
        }
    }

    fun subMedCountAndGetOrderInfo(userId: Int, mOrderId: Int) {
        launch {
            //todo 前面出现404怎么办？返回值只有一个
            repository.subMedCount(mOrderId, userId)
            val response = repository.getMedCartList(userId)
            cartLiveData.value = response.data
            return@launch response
        }
    }

    fun postOrder(userOrderInfo: UserOrderInfo): LiveData<BaseResponse<Any>> = emit {
        repository.postOrder(
            Settings.Account.userId.toInt(),
            userOrderInfo.phone,
            userOrderInfo.phone,
            userOrderInfo.address,
            userOrderInfo.speed
        )
    }
}