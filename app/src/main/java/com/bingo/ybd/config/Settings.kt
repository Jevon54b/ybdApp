package com.bingo.ybd.config


import com.bingo.ybd.constant.Constant
import com.bingo.ybd.data.storage.Preference


object Settings {

    var needReloadCart by Preference(Constant.KEY_LOAD_CART, true)

    //Preference代理存储用户信息
    object Account {
        var userId by Preference(Constant.KEY_USER_ID, "")
        var userName by Preference(Constant.KEY_USER_NICK, "")
        var userPassword by Preference(Constant.KEY_PASSWORD, "")
        var userMoney by Preference(Constant.KEY_USER_MONEY, "")
    }

}