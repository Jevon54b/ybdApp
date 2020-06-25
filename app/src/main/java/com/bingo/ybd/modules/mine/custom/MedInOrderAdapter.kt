package com.bingo.ybd.modules.mine.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bingo.ybd.R
import com.bingo.ybd.base.adapter.BaseRecyclerAdapter
import com.bingo.ybd.data.model.MedBrief
import com.bingo.ybd.data.model.MedInOrder
import com.bumptech.glide.Glide

class MedInOrderAdapter(private val mContext: Context) :
    BaseRecyclerAdapter<MedInOrder, MedInOrderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_med_order, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        Glide.with(mContext).load(data.pic).into(holder.medImg)
        holder.medNameText.text = data.name
        holder.medPackingSizeText.text = data.packingSize
        holder.medNumText.text = "x${data.medNum}"
        holder.medPriceText.text = "￥${data.price}"
    }


    class ViewHolder(mView:View):RecyclerView.ViewHolder(mView){
        val medImg: ImageView = mView.findViewById(R.id.medImg)
        val medNameText:TextView = mView.findViewById(R.id.medNameText)
        val medPackingSizeText:TextView = mView.findViewById(R.id.medPackingSizeText)
        val medPriceText:TextView = mView.findViewById(R.id.medPriceText)
        val medNumText:TextView = mView.findViewById(R.id.medNumText)
    }
}