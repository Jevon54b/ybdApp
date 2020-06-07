package com.bingo.ybd.modules.main.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bingo.ybd.R
import com.bingo.ybd.modules.main.model.MedBriefModel
import com.bumptech.glide.Glide

class MedShowAdpter(val medList:List<MedBriefModel>,val context: Context): RecyclerView.Adapter<MedShowAdpter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_med_show, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return medList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val medData = medList.get(position)
        Glide.with(context).load(medData.pic).into(holder.medImg)
        holder.medNameText.setText(medData.name)
        holder.medFunctionsText.setText(medData.note)
        holder.medPriceText.setText("￥${medData.price}")
        holder.medSalesNumText.setText("销量:${medData.salesNum}")

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