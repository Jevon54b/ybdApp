package com.bingo.ybd.modules.user.activity

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.config.Settings
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.modules.main.activity.MainActivity
import com.bingo.ybd.modules.user.vm.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseVMActivity(){

    companion object {
        const val TAG = "LoginActivity"
    }

    private val mLoginViewModel: LoginViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun getViewModel(): BaseViewModel = mLoginViewModel


    override fun initView() {
        userLoginBtn.setOnClickListener {
            login()
        }
        registerText.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, Constant.REQUEST_CODE_FOR_REGISTER)
        }
    }

    override fun initData() {
        //show saved password for next login
        if (!Settings.Account.userPassword.isNullOrEmpty()){
            phoneEdit.setText(Settings.Account.userPhone)
            passwordEdit.setText(Settings.Account.userPassword)
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
        mLoginViewModel.userLogin(phone, password).observe(this,
            Observer {
                it.data.let {
                    Settings.Account.apply {
                        userPhone = phone
                        userId = it.id
                        userName = it.nick ?: ""
                        userMoney = it.money
                        userPassword = it.password
                    }
                }
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.REQUEST_CODE_FOR_REGISTER) {
            when (resultCode) {
                Constant.RESULT_CODE_TO_LOGIN -> {
                    data?.let {
                        phoneEdit.setText(data.getStringExtra(Constant.KEY_PHONE))
                        passwordEdit.setText(data.getStringExtra(Constant.KEY_PASSWORD))
                    }
                }
                else -> {

                }
            }
        }
    }


}