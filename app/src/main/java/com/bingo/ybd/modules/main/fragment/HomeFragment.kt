package com.bingo.ybd.modules.main.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bingo.ybd.R
import com.bingo.ybd.base.fragment.BaseVMFragment
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.data.model.MedBrief
import com.bingo.ybd.modules.main.custom.ImageBannerAdapter
import com.bingo.ybd.modules.main.custom.MedShowAdpter
import com.bingo.ybd.modules.main.vm.MainViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment:BaseVMFragment() {

    lateinit var medList: MutableList<MedBrief>

    val mainViewModel: MainViewModel by viewModel()

    lateinit var medShowAdapter: MedShowAdpter

    lateinit var imageBannerAdapter: ImageBannerAdapter

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun getViewModel(): BaseViewModel = mainViewModel

    override fun initView() {
        initBanner()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        medShowAdapter = MedShowAdpter(requireContext())
        recyclerView.adapter = medShowAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initBanner() {
        medList = ArrayList()
        imageBannerAdapter = ImageBannerAdapter(requireContext(), medList)
        banner.adapter = imageBannerAdapter
        banner.indicator = CircleIndicator(requireContext())
        banner.start()
    }

    override fun initData() {
        mainViewModel.getTop5MedList().observe(this, Observer {
            medShowAdapter.replaceAll(it.data)
            medShowAdapter.notifyDataSetChanged()
            imageBannerAdapter.replaceAll(it.data)
            imageBannerAdapter.notifyDataSetChanged()
        })
    }

    override fun onStop() {
        super.onStop()
        banner.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        banner.destroy()
    }
}