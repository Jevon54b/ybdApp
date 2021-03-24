package com.bingo.ybd.modules.mine.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bingo.ybd.R
import com.bingo.ybd.base.adapter.BaseRecyclerAdapter
import com.bingo.ybd.data.model.PointInfo

class PoiInfoAdapter(val context:Context) : BaseRecyclerAdapter<PointInfo,PoiInfoAdapter.ViewHolder>(){

    private var itemClickListener: ((PointInfo)->Unit)? = null

    public fun setOnItemClickListener(itemClickListener:((PointInfo)->Unit)){
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_poi_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val poiInfo = list[position]
        holder.poiNameText.text = poiInfo.poiName
        holder.poiAddressText.text = poiInfo.poiAddress
        holder.chooseBtn.setOnClickListener {
            itemClickListener?.invoke(poiInfo)
        }

    }


    class ViewHolder(mItemView: View) : RecyclerView.ViewHolder(mItemView) {
        val poiNameText: TextView = mItemView.findViewById(R.id.poiNameText)
        val poiAddressText: TextView = mItemView.findViewById(R.id.poiAddressText)
        val chooseBtn: Button = mItemView.findViewById(R.id.chooseBtn)
    }
}