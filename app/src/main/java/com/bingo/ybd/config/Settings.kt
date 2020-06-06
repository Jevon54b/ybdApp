package com.bingo.ybd.config


import com.bingo.ybd.constant.Constant
import com.bingo.ybd.data.storage.Preference


object Settings {

    object Account {
        var loginUser by Preference(Constant.LOGIN_USER, "")
        var userName by Preference(Constant.USER_NAME, "")
        var password by Preference(Constant.USER_PASSWORD, "")
    }
}