package com.bingo.ybd.base.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.data.model.BaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    companion object{
        const val TAG = "BaseViewModel"
    }

    val mStateLiveData = MutableLiveData<StateActionEvent>()//通用事件模型驱动(如：显示对话框、取消对话框、错误提示)

    fun <T> launch(block: suspend CoroutineScope.() -> BaseResponse<T>) {//使用协程封装统一的网络请求处理
        viewModelScope.launch {
            try {
                mStateLiveData.value = LoadState
                val response = block()
                if (response.status == Constant.RESPONSE_SUCCESS) {
                    mStateLiveData.value = SuccessState
                }else{
                    mStateLiveData.value = ErrorState(response.msg)
                }
            } catch (e: Exception) {
                mStateLiveData.value = ErrorState(e.message)
                e.printStackTrace()
            }
        }
    }

    fun <T> emit(block: suspend LiveDataScope<BaseResponse<T>>.() -> BaseResponse<T>): LiveData<BaseResponse<T>> =
        liveData {
            try {
                mStateLiveData.value = LoadState
                val response = block()
                if (response.status == Constant.RESPONSE_SUCCESS) {
                    emit(response)
                    mStateLiveData.value = SuccessState
                } else {
                    mStateLiveData.value = ErrorState(response.msg)
                }
            } catch (e: Exception) {
                mStateLiveData.value = ErrorState(e.message)
                e.printStackTrace()
                Log.e(TAG, e.message)
            }
        }

}