package com.bingo.ybd


import com.bingo.ybd.data.repository.Repository
import com.bingo.ybd.modules.disc.vm.DiscViewModel
import com.bingo.ybd.modules.main.vm.MainViewModel
import com.bingo.ybd.modules.mine.vm.MineViewModel
import com.bingo.ybd.modules.mine.vm.SupportViewModel
import com.bingo.ybd.modules.shop.vm.CartViewModel
import com.bingo.ybd.modules.user.vm.LoginViewModel
import com.bingo.ybd.modules.user.vm.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(Repository) }
    viewModel { RegisterViewModel(Repository) }
    viewModel { MainViewModel(Repository) }
    viewModel { CartViewModel(Repository) }
    viewModel { SupportViewModel(Repository) }
    viewModel { DiscViewModel(Repository) }
    viewModel { MineViewModel() }
}


val appModule = listOf(viewModelModule)