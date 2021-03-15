package com.bingo.ybd.modules.disc.vm

import androidx.lifecycle.LiveData
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.data.model.Article
import com.bingo.ybd.data.model.BaseResponse
import com.bingo.ybd.data.model.SupportInfo
import com.bingo.ybd.data.repository.Repository

class DiscViewModel(private val repository:Repository):BaseViewModel() {
    fun getArticleList(): LiveData<BaseResponse<List<Article>>> = emit {
        repository.getArticleList()
    }
}