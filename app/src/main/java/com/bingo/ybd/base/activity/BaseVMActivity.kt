package com.bingo.ybd.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.bingo.ybd.R
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.base.viewmodel.ErrorState
import com.bingo.ybd.base.viewmodel.LoadState
import com.bingo.ybd.base.viewmodel.SuccessState
import com.bingo.ybd.ext.errorToast



abstract class BaseVMActivity : AppCompatActivity(){

    private lateinit var loadingPopup:MaterialDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout()
    }

    protected fun initViewModelAction() {
        getViewModel().let { baseViewModel ->
            baseViewModel.mStateLiveData.observe(this, Observer { stateActionState ->
                when (stateActionState) {
                    LoadState -> showLoading()
                    SuccessState -> dismissLoading()
                    is ErrorState -> {
                        dismissLoading()
                        stateActionState.message?.apply {
                            errorToast(this)
                            handleError()
                        }
                    }
                }
            })
        }
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun getViewModel(): BaseViewModel

    open fun setContentLayout() {
        setContentView(getLayoutId())
        initViewModelAction()
        initView()
        initData()
    }

    open fun initData() {

    }

    open fun showLoading() {
        if (!::loadingPopup.isInitialized || !loadingPopup.isShowing) {
            loadingPopup = MaterialDialog(this).show {
                customView(R.layout.popup_loading)
                lifecycleOwner(this@BaseVMActivity)
            }
        }
    }

    open fun dismissLoading() {
        if(loadingPopup.isShowing){
            loadingPopup.hide()
        }
    }

    open fun handleError() {

    }
}