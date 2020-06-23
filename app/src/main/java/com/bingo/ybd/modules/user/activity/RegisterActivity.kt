package com.bingo.ybd.modules.user.activity

import android.content.Intent
import androidx.lifecycle.Observer
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.ext.errorToast
import com.bingo.ybd.ext.successToast
import com.bingo.ybd.modules.user.vm.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity:BaseVMActivity() {

    private val registerViewModel: RegisterViewModel by viewModel()

    override fun getViewModel(): BaseViewModel = registerViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
        loginText.setOnClickListener {
            finish()
        }
        registerBtn.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val nick = nickEdit.text.toString()
        val phone = phoneEdit.text.toString()
        val password = passwordEdit.text.toString()
        val againPassword = passwordAgainEdit.text.toString()
        if (nick.isBlank() || phone.isBlank() || password.isBlank() || againPassword.isBlank()) {
            errorToast("非法空输入")
            return
        }
        if (password != againPassword) {
            errorToast("两次输入的密码不一致")
            return
        }


        registerViewModel.userRegister(nick, phone, password).observe(this, Observer {
            successToast("注册成功,自动跳转到登录页")
            val intent = Intent()
            intent.putExtra(Constant.KEY_PHONE, phone)
            intent.putExtra(Constant.KEY_PASSWORD, password)
            setResult(Constant.RESULT_CODE_TO_LOGIN, intent)
            finish()
        })
    }


}