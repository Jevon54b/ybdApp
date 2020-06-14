package com.bingo.ybd.modules.shop.custom

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

class MedPostItemAdapter(val context: Context, val medList: List<MedBriefModel>) :
    RecyclerView.Adapter<MedPostItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_med_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return medList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = medList.get(position)
        Glide.with(context).load(data.pic).into(holder.medImg)
        holder.medNameText.text = data.name
        holder.medPackingSizeText.text = "规格:${data.packingSize}"
        holder.medNumText.text = "x${data.medNum}"
        holder.medPriceText.text = "￥${data.price}"
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val medImg: ImageView = view.findViewById(R.id.medImg)
        val medNameText: TextView = view.findViewById(R.id.medNameText)
        val medPackingSizeText: TextView = view.findViewById(R.id.medPackingSizeText)
        val medPriceText: TextView = view.findViewById(R.id.medPriceText)
        val medNumText: TextView = view.findViewById(R.id.medNumText)
    }
}