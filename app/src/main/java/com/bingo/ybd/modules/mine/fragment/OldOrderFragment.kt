package com.bingo.ybd.modules.mine.fragment

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bingo.ybd.R
import com.bingo.ybd.base.fragment.BaseVMFragment
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.modules.mine.custom.OrderAdapter
import com.bingo.ybd.modules.mine.vm.MineViewModel
import kotlinx.android.synthetic.main.fragment_current_order.*
import kotlinx.android.synthetic.main.fragment_old_order.*
import kotlinx.android.synthetic.main.fragment_old_order.noItemImg
import org.koin.androidx.viewmodel.ext.android.viewModel

class OldOrderFragment:BaseVMFragment() {

    private val mineViewModel: MineViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.fragment_old_order

    override fun getViewModel(): BaseViewModel = mineViewModel

    private lateinit var orderAdapter: OrderAdapter

    override fun initView() {
        orderAdapter = OrderAdapter(requireContext())
        finishedOrderRecyclerView.adapter = orderAdapter
        finishedOrderRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun initData() {
        mineViewModel.finishedOrderListData.observe(this, Observer {
            orderAdapter.replaceAll(it)
            orderAdapter.notifyDataSetChanged()
            if(it == null || it.isEmpty()){
                noItemImg.visibility = View.VISIBLE
                oldOrderListLayout.setBackgroundColor(resources.getColor(R.color.colorWhite))
            }else{
                noItemImg.visibility = View.GONE
                oldOrderListLayout.setBackgroundColor(resources.getColor(R.color.colorBackGray))
            }
        })
        mineViewModel.getFinishedOrderListData()
    }
}