package com.bingo.ybd.modules.mine.activity

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.modules.mine.custom.AddressAdapter
import com.bingo.ybd.modules.mine.vm.MineViewModel
import com.fondesa.recyclerviewdivider.dividerBuilder
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.activity_med_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddressListActivity : BaseVMActivity() {

    override fun getLayoutId(): Int = R.layout.activity_address_list

    override fun getViewModel(): BaseViewModel = mineViewModel

    private val mineViewModel: MineViewModel by viewModel()

    private lateinit var addressAdapter: AddressAdapter

    private var fromPostOrder = false

    override fun initView() {
        addressAdapter = AddressAdapter(this)
        addressRecyclerView.adapter = addressAdapter
        addressRecyclerView.layoutManager = LinearLayoutManager(this)
        this.dividerBuilder()
            .showLastDivider()
            .build()
            .addTo(addressRecyclerView)
        addAddressButton.setOnClickListener {
            val intent = Intent(this,AddressDetailActivity::class.java)
            intent.putExtra(Constant.KEY_ADDRESS_DETAIL_TYPE,Constant.VALUE_ADDRESS_CREATE)
            startActivity(intent)
        }

        if(intent.getStringExtra(Constant.KEY_ADDRESS_LIST_TYPE) == Constant.VALUE_FROM_POST_ORDER ) {
            fromPostOrder = true
            addressAdapter.setOnItemClickListener {
                intent.putExtra(Constant.KEY_ADDRESS_INFO,it)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }

    }

    override fun initData() {
        mineViewModel.addressInfoList.observe(this, Observer {
            if (it.isEmpty()){
                noItemText.visibility = View.VISIBLE
            }else{
                noItemText.visibility = View.GONE
            }
            addressAdapter.replaceAll(it)
            addressAdapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        mineViewModel.getAddressInfoList()
    }


}