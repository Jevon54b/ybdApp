package com.bingo.ybd.data.model

data class BaseResponse<T>(
    val status: String,
    val msg: String,
    val data: T
)