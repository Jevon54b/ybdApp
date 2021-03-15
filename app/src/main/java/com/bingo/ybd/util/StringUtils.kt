package com.bingo.ybd.util

import java.text.SimpleDateFormat
import java.util.*

object StringUtils {
    fun getCurrentTimeStr(): String {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") //设置日期格式
        return df.format(Date()) // new Date()为获取当前系统时间
    }
}