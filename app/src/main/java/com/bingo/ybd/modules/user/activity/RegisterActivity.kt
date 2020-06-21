package com.bingo.ybd.modules.user.activity

import androidx.activity.viewModels
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
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
        //registerViewModel.userRegister()
    }


}