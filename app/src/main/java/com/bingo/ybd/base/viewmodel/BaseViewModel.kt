package com.bingo.ybd.base.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.bingo.ybd.data.model.BaseResponse
import com.bingo.ybd.modules.user.vm.LoginViewModel.Companion.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    val mStateLiveData = MutableLiveData<StateActionEvent>()//通用事件模型驱动(如：显示对话框、取消对话框、错误提示)

    fun launch(block: suspend CoroutineScope.() -> Unit) {//使用协程封装统一的网络请求处理
        viewModelScope.launch {
            try {
                mStateLiveData.value = LoadState
                block()
                mStateLiveData.value = SuccessState
            } catch (e: Exception) {
                e.printStackTrace()
                mStateLiveData.value = ErrorState(e.message)
            }
        }
    }

    //todo 理解高级函数
    fun <T> emit(block: suspend LiveDataScope<BaseResponse<T>>.() -> BaseResponse<T>): LiveData<BaseResponse<T>> =
        liveData {
            viewModelScope.launch {
                Log.e(TAG, "TEST3")
                try {
                    mStateLiveData.value = LoadState
                    val response = block()
                    if (response.status == "200") {
                        emit(response)
                        mStateLiveData.value = SuccessState
                    } else {
                        mStateLiveData.value = ErrorState(response.msg)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    mStateLiveData.value = ErrorState(e.message)
                }
        }
    }

}