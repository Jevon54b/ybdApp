package com.bingo.ybd.modules.shop.custom

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

class MedCartItemAdapter(val mContext: Context) :
    BaseRecyclerAdapter<MedInOrder, MedCartItemAdapter.ViewHolder>() {

    var medCountChangeListener: MedCountChangeListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.item_med_cart, parent, false)
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
        holder.medNumText.text = "${data.medNum}"
        holder.medPriceText.text = "￥${data.price}"
        holder.addImg.setOnClickListener {
            medCountChangeListener?.onAdd(position)
        }
        holder.subImg.setOnClickListener {
            medCountChangeListener?.onSub(position)
        }
    }


    class ViewHolder(mView:View):RecyclerView.ViewHolder(mView){
        val medImg: ImageView = mView.findViewById(R.id.medImg)
        val medNameText: TextView = mView.findViewById(R.id.medNameText)
        val medPackingSizeText: TextView = mView.findViewById(R.id.medPackingSizeText)
        val medPriceText: TextView = mView.findViewById(R.id.medPriceText)
        val medNumText: TextView = mView.findViewById(R.id.medNumText)
        val addImg:ImageView = mView.findViewById(R.id.addImg)
        val subImg:ImageView = mView.findViewById(R.id.subImg)
    }

    interface MedCountChangeListener{
        fun onSub(position: Int)
        fun onAdd(position: Int)
    }
}