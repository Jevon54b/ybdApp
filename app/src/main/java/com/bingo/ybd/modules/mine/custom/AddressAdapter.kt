package com.bingo.ybd.modules.mine.custom

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bingo.ybd.R
import com.bingo.ybd.base.adapter.BaseRecyclerAdapter
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.data.model.AddressInfo
import com.bingo.ybd.modules.mine.activity.AddressDetailActivity
import com.bingo.ybd.modules.mine.activity.MapActivity

class AddressAdapter(val context: Context) :
    BaseRecyclerAdapter<AddressInfo, AddressAdapter.ViewHolder>() {

    private var itemClickListener: ((AddressInfo)-> Unit)? = null

    public fun setOnItemClickListener(itemClickListener : ((AddressInfo)-> Unit)){
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_address_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val addressInfo = list[position]
        holder.addressText.text = addressInfo.address
        holder.nameText.text = addressInfo.userName
        holder.phoneText.text = addressInfo.phone
        holder.editButton.setOnClickListener {
            val intent = Intent(context,AddressDetailActivity::class.java)
            intent.putExtra("id",addressInfo.id)
            intent.putExtra(Constant.KEY_ADDRESS_DETAIL_TYPE,Constant.VALUE_ADDRESS_UPDATE)
            context.startActivity(intent)
        }
        holder.itemView.setOnClickListener {
            this.itemClickListener?.invoke(addressInfo)
        }
    }

    class ViewHolder(mItemView: View) : RecyclerView.ViewHolder(mItemView) {
        val addressText: TextView = mItemView.findViewById(R.id.addressText)
        val phoneText: TextView = mItemView.findViewById(R.id.phoneText)
        val nameText: TextView = mItemView.findViewById(R.id.nameText)
        val editButton: ImageView = mItemView.findViewById(R.id.editButton)
    }
}