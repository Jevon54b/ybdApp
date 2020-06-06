package com.bingo.ybd.modules.user.activity

import android.content.Intent
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.modules.user.vm.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseVMActivity(){

    private val mLoginViewModel: LoginViewModel by viewModel()

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        registerText.setOnClickListener {
            intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(){
//        if (username.text.toString().isNullOrEmpty()){
//            return
//        }
//        if(password.text.toString().isNullOrEmpty()) {
//            return
//        }
//        Log.d("test","test")
//        mLoginViewModel.userLogin(username.text.toString(),password.text.toString())
    }

    override fun getViewModel(): BaseViewModel {
        return mLoginViewModel
    }






}