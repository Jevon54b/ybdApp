package com.bingo.ybd.util

import java.text.SimpleDateFormat
import java.util.*

object StringUtils {
    fun getCurrentTimeStr(): String {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") //设置日期格式
        return df.format(Date()) // new Date()为获取当前系统时间
    }

    fun convertTimeStampToFormat(timeStampStr:String): String{
        var time = Date()
        time.time = timeStampStr.toLong()
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time)
    }
}