package com.bingo.ybd.data.network

import com.bingo.ybd.data.model.*
import retrofit2.http.*

interface ApiService {
//    @GET("v2/place?token=${BingoWeatherApplication.token}&lang=zh_CN")
//    fun searchPlaces(@Query("query") query:String): Call<PlaceResponse>

    /* 用户模块 */

    @FormUrlEncoded
    @POST("user/login.do")
    suspend fun login(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): BaseResponse<UserInfo>


    @FormUrlEncoded
    @POST("user/register.do")
    suspend fun register(
        @Field("nick") nick: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ): BaseResponse<Any>


    /* 药品模块 */

    @GET("medicine/getTop5MedList.do")
    suspend fun getTop5MedList(): BaseResponse<List<MedBrief>>

    @GET("medicine/getMedicineListByTypeId.do")
    suspend fun getMedListByTypeId(
        @Query("type_id") typeId: Int,
        @Query("sort_flag") sortFlag: Int
    ): BaseResponse<List<MedBrief>>


    @GET("medicine/getMedicineDetail.do")
    suspend fun getMedDetail(@Query("med_id") medId: Int): BaseResponse<MedDetail>


    @GET("medicine/searchMed.do")
    suspend fun searchMed(
        @Query("keyword") keyword: String,
        @Query("sort_flag") sortFlag: Int
    ): BaseResponse<List<MedBrief>>


    /* 订单模块 */

    @FormUrlEncoded
    @POST("order/addMedToCurOrder.do")
    suspend fun addMedToCurOrder(
        @Field("user_id") userId: Int,
        @Field("med_id") medId: Int
    ): BaseResponse<Any>


    @GET("order/getMedListFromCurOrder.do")
    suspend fun getMedCartList(@Query("user_id") userId: Int): BaseResponse<List<MedInOrder>>

    @FormUrlEncoded
    @POST("order/subMednum.do")
    suspend fun subMedCount(
        @Field("om_id") medOrderId: Int,
        @Field("user_id") userId: Int
    ): BaseResponse<Any>

    @FormUrlEncoded
    @POST("order/postNormalOrder.do")
    suspend fun postOrder(
        @Field("user_id") userId: Int,
        @Field("user_phone") userPhone: String,
        @Field("user_name") userName: String,
        @Field("user_address") userAddress: String,
        @Field("speed") speed: Int
    ): BaseResponse<Any>

    @GET("order/getStartingOrderByUserId.do")
    suspend fun getStartingOrderList(@Query("user_id") userId: Int): BaseResponse<List<Order>>

    @GET("order/getFinishedOrderByUserId.do")
    suspend fun getFinishedOrderList(@Query("user_id") userId: Int): BaseResponse<List<Order>>


    /* 发现模块 */

    @GET("disc/getArticleList.do")
    suspend fun getArticleList(): BaseResponse<List<Article>>

    @FormUrlEncoded
    @POST("disc/getArticleDetail.do")
    suspend fun getArticleDetail(@Field("id") articleId: Int): BaseResponse<Article>

    @FormUrlEncoded
    @POST("disc/getArticleComment.do")
    suspend fun getArticleCommentList(@Field("id") articleId: Int): BaseResponse<List<Comment>>

    @FormUrlEncoded
    @POST("disc/addComment.do")
    suspend fun addComment(
        @Field("article_id") articleId: Int,
        @Field("user_id") userId: Int,
        @Field("content") content: String,
        @Field("username") username: String
    ): BaseResponse<Any>


    @GET("message/getSupportInfo.do")
    suspend fun getSupportInfo():BaseResponse<SupportInfo>
}