package com.bingo.ybd.modules.shop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.bingo.ybd.R
import com.bingo.ybd.base.fragment.BaseVMFragment
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.config.Settings
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.data.model.MedInOrder
import com.bingo.ybd.modules.shop.custom.MedCartItemAdapter
import com.bingo.ybd.modules.shop.vm.CartViewModel
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlinx.android.synthetic.main.fragment_shop.recyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShopFragment: BaseVMFragment() {

    companion object {
        const val TAG = "ShopFragment"
    }

    lateinit var cartItemAdapter: MedCartItemAdapter

    private var medList: List<MedInOrder>? = null

    val cartViewModel: CartViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.fragment_shop

    override fun getViewModel(): BaseViewModel = cartViewModel

    override fun initView() {
        cartItemAdapter = MedCartItemAdapter(requireContext())
        cartItemAdapter.medCountChangeListener = initMedCountChangeListener()
        recyclerView.adapter = cartItemAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        postOrderBtn.setOnClickListener {
            medList?.let {
                val intent = Intent(requireContext(), PostOrderActivity::class.java)
                intent.putParcelableArrayListExtra(Constant.KEY_CART_LIST, ArrayList(it))
                requireContext().startActivity(intent)
            }
        }
    }

    override fun initData() {
        super.initData()
        cartViewModel.cartLiveData.observe(this,
            Observer {
                medList = it
                loadDataToView(it)
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Settings.needReloadCart = true //onCreate时候需要loadCart
    }

    override fun onStart() {
        super.onStart()
        if (Settings.needReloadCart) {
            cartViewModel.getMedListInCart(Settings.Account.userId.toInt())
        }
    }


    private fun loadDataToView(data: List<MedInOrder>) {
        Settings.needReloadCart = false
        if (data.isNotEmpty()) {
            if (noItemImg.visibility == View.VISIBLE) {
                noItemImg.visibility = View.GONE
            }
            if (cartBottomView.visibility == View.INVISIBLE) {
                cartBottomView.visibility == View.VISIBLE
            }
            cartItemAdapter.replaceAll(data)
            cartItemAdapter.notifyDataSetChanged()
            totalSumText.text = "药品总额:￥${data[0].totalSum}"
        } else {
            cartItemAdapter.clear()
            cartItemAdapter.notifyDataSetChanged()
            cartBottomView.visibility = View.INVISIBLE
            noItemImg.visibility = View.VISIBLE
        }
    }


    //增减药品事件
    private fun initMedCountChangeListener()
            : MedCartItemAdapter.MedCountChangeListener =
        object : MedCartItemAdapter.MedCountChangeListener {
            override fun onSub(position: Int) {
                val curMed = cartItemAdapter.list[position]
                //当药品数量剩下1时，继续减则等同于删除该条目
                if (curMed.medNum.toInt() == 1) {
                    MaterialDialog(requireContext()).show {
                        title(R.string.title_attention)
                        message(R.string.msg_delete_med)
                        positiveButton(R.string.ensure_text) {
                            cartViewModel.subMedCountAndGetOrderInfo(
                                Settings.Account.userId.toInt(),
                                cartItemAdapter.list[position].mOrderId
                            )
                        }
                        negativeButton(R.string.comment_cancel)
                    }
                } else {
                    cartViewModel.subMedCountAndGetOrderInfo(
                        Settings.Account.userId.toInt(),
                        cartItemAdapter.list[position].mOrderId
                    )
                }
            }

            override fun onAdd(position: Int) {
                cartViewModel.addMedCountAndGetOrderInfo(
                    Settings.Account.userId.toInt(),
                    cartItemAdapter.list[position].medId
                )
            }

        }

}