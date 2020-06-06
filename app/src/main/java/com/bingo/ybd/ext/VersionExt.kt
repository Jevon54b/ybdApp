package com.bingo.ybd.ext

import com.bingo.ybd.AppContext

fun getVersionName(): String {
    return AppContext.packageManager.getPackageInfo(AppContext.packageName, 0).versionName
}