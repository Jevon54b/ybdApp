package com.bingo.ybd.data.repository

import com.bingo.ybd.R
import com.bingo.ybd.data.model.*
import com.bingo.ybd.data.network.ApiService
import com.bingo.ybd.data.network.ServiceCreator

object Repository {

    private val apiService = ServiceCreator.create<ApiService>()


    suspend fun userLogin(phone: String, password: String): BaseResponse<UserInfo> {
        return apiService.login(phone, password)
    }


    suspend fun userRegiser(nick: String, phone: String, password: String): BaseResponse<Any> {
        return apiService.register(nick, phone, password)
    }

    suspend fun getTop5MedList(): BaseResponse<List<MedBrief>> = apiService.getTop5MedList()


    suspend fun getMedListByTypeId(
        typeId: Int, sortFlag: Int
    ): BaseResponse<List<MedBrief>> = apiService.getMedListByTypeId(typeId, sortFlag)


    suspend fun getMedDetail(medId: Int): BaseResponse<MedDetail> = apiService.getMedDetail(medId)

    suspend fun searchMed(
        keyword: String, sortFlag: Int
    ): BaseResponse<List<MedBrief>> = apiService.searchMed(keyword, sortFlag)

    suspend fun addMedToCurOrder(
        userId: Int, medId: Int
    ): BaseResponse<Any> = apiService.addMedToCurOrder(userId, medId)

    suspend fun getMedCartList(userId: Int): BaseResponse<List<MedInOrder>> = apiService.getMedCartList(userId)

    suspend fun subMedCount(
        medOrderId: Int, userId: Int
    ): BaseResponse<Any> = apiService.subMedCount(medOrderId, userId)

    suspend fun postOrder(
        userId: Int, userPhone: String,
        userName: String, userAddress: String, speed: Int
    ): BaseResponse<Any> = apiService.postOrder(userId, userPhone, userName, userAddress, speed)

    suspend fun getStartingOrderList(userId: Int): BaseResponse<List<Order>> = apiService.getStartingOrderList(userId)

    suspend fun getFinishedOrderList(userId: Int): BaseResponse<List<Order>> = apiService.getFinishedOrderList(userId)

    suspend fun getArticleList(): BaseResponse<Article> = apiService.getArticleList()

    suspend fun getArticleDetail(articleId: Int): BaseResponse<Article> = apiService.getArticleDetail(articleId)

    suspend fun getArticleCommentList(articleId: Int): BaseResponse<List<Comment>> =
        apiService.getArticleCommentList(articleId)

    suspend fun addComment(
        articleId: Int, userId: Int, content: String, username: String
    ): BaseResponse<List<Comment>> = apiService.addComment(articleId, userId, content, username)

    fun getUserPhoto(): Int {
        var photoList = listOf(
            R.mipmap.p0,
            R.mipmap.p1,
            R.mipmap.p2,
            R.mipmap.p3,
            R.mipmap.p4,
            R.mipmap.p5,
            R.mipmap.p6)
        var randomIndex = (0..6).random()
        return photoList[randomIndex]
    }

}