package com.bingo.ybd.modules.mine.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bingo.ybd.R
import com.bingo.ybd.modules.mine.model.OrderModel
import com.fondesa.recyclerviewdivider.dividerBuilder

class OrderAdpter(val mContext:Context,val orderList:List<OrderModel>):RecyclerView.Adapter<OrderAdpter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_order,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = orderList.get(position)
        holder.payTimeText.text = data.payTime
        if (data.needCheck==0){
            holder.orderStateText.text = when(data.state){
                1 -> "等待接单中"
                2 -> "订单派送中"
                3 -> "订单已完成"
                -1 -> "订单已取消"
                else -> ""
            }
        }else{
            holder.orderStateText.text = "等待审核中"
        }

        holder.orderPriceText.text = data.totalSum.toString()

        val medAdpter = MedInOrderAdapter(mContext,data.medList)
        holder.medRecyclerView.adapter = medAdpter
        holder.medRecyclerView.layoutManager = LinearLayoutManager(mContext)

        mContext.dividerBuilder()
            .color(mContext.getColor(R.color.colorWhite))
            .build()
            .addTo(holder.medRecyclerView)

    }

    class ViewHolder(mView:View):RecyclerView.ViewHolder(mView){
        val payTimeText:TextView = mView.findViewById(R.id.payTimeText)
        val orderStateText:TextView = mView.findViewById(R.id.orderStateText)
        val medRecyclerView:RecyclerView = mView.findViewById(R.id.medRecyclerView)
        val orderPriceText:TextView = mView.findViewById(R.id.orderPriceText)
    }
}