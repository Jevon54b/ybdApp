package com.bingo.ybd.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.jessyan.autosize.internal.CustomAdapt

abstract class BaseActivity : AppCompatActivity(), CustomAdapt {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout()
    }

    open fun setContentLayout() {
        setContentView(getLayoutId())
        initView()
        initData()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    open fun initData() {

    }

    override fun isBaseOnWidth(): Boolean {
        return true
    }

    override fun getSizeInDp(): Float {
        return 414f
    }
}