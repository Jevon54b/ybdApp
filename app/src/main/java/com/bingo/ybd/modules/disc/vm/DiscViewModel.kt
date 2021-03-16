package com.bingo.ybd.modules.disc.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.data.model.*
import com.bingo.ybd.data.repository.Repository

class DiscViewModel(private val repository:Repository):BaseViewModel() {

    val commentLiveData: MutableLiveData<List<Comment>> = MutableLiveData()


    fun getArticleList(): LiveData<BaseResponse<List<Article>>> = emit {
        repository.getArticleList()
    }

    fun getArticleDetail(articleId:Int): LiveData<BaseResponse<Article>> = emit{
        repository.getArticleDetail(articleId)
    }

    fun getArticleCommentList(articleId: Int) {
        launch {
            val response = repository.getArticleCommentList(articleId)
            commentLiveData.value = response.data
            return@launch response
        }
    }

    fun addComment(articleId: Int, userId: Int, content: String, username: String){
        launch {
            val addResponse = repository.addComment(articleId, userId, content, username)
            val listResponse = repository.getArticleCommentList(articleId)
            commentLiveData.value = listResponse.data
            return@launch listResponse
        }
    }
}
