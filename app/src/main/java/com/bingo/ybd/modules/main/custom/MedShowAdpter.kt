package com.bingo.ybd.modules.main.custom

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
import com.bumptech.glide.Glide

class MedShowAdpter(val context: Context) :
    BaseRecyclerAdapter<MedBrief, MedShowAdpter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_med_show, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val medData = list[position]
        Glide.with(context).load(medData.pic).into(holder.medImg)
        holder.medNameText.text = medData.name
        holder.medFunctionsText.text = medData.note
        holder.medPriceText.text = "￥${medData.price}"
        holder.medSalesNumText.text = "销量:${medData.salesNum}"

        //监听点击事件
    }


    class ViewHolder(mItemView:View):RecyclerView.ViewHolder(mItemView){
        val medImg:ImageView = mItemView.findViewById(R.id.medImg)
        val medNameText:TextView = mItemView.findViewById(R.id.medNameText)
        val medFunctionsText:TextView = mItemView.findViewById(R.id.medFunctionsText)
        val medPriceText:TextView = mItemView.findViewById(R.id.medPriceText)
        val medSalesNumText:TextView = mItemView.findViewById(R.id.medSalesNumText)
    }
}