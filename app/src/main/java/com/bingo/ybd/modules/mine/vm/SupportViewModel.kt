package com.bingo.ybd.modules.mine.vm

import androidx.lifecycle.LiveData
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.data.model.BaseResponse
import com.bingo.ybd.data.model.MedBrief
import com.bingo.ybd.data.model.SupportInfo
import com.bingo.ybd.data.repository.Repository

class SupportViewModel(private val repository: Repository) : BaseViewModel(){
    fun getSupportInfo(): LiveData<BaseResponse<SupportInfo>> = emit {
        repository.getSupportInfo()
    }
}