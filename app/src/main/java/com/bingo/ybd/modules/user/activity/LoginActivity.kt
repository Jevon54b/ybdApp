package com.bingo.ybd.modules.user.activity

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.data.model.BaseResponse
import com.bingo.ybd.data.model.UserInfo
import com.bingo.ybd.modules.user.vm.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseVMActivity(){

    private val mLoginViewModel: LoginViewModel by viewModel()

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        userLoginBtn.setOnClickListener {
            login()
        }
        registerText.setOnClickListener {
            intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(){
        val phone = phoneEdit.text.toString()
        val password = passwordEdit.text.toString()
        if (phone.isNullOrEmpty()) {
            return
        }
        if (password.isNullOrEmpty()) {
            return
        }
        Log.d("test", "test")
        mLoginViewModel.userLogin(phone, password).observe(this,
            Observer<BaseResponse<UserInfo>> {
                Log.e("Test", it.toString())
            })
        // Log.e("test",data.value.toString())
    }

    override fun getViewModel(): BaseViewModel {
        return mLoginViewModel
    }






}