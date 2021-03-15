package com.bingo.ybd.modules.mine.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bingo.lib.CustomImageView
import com.bingo.ybd.R
import com.bingo.ybd.base.adapter.BaseRecyclerAdapter
import com.bingo.ybd.data.model.Message

class MessageAdapter(private val mContext:Context)
    : BaseRecyclerAdapter<Message, RecyclerView.ViewHolder>(){

    companion object {
        private const val MSG_TYPE_FROM_SUPPORT =  0;
        private const val MSG_TYPE_FROM_USER = 1;
    }

    private var mInflater:LayoutInflater = LayoutInflater.from(mContext);

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            MessageAdapter.MSG_TYPE_FROM_SUPPORT -> LeftViewHolder(mInflater.inflate(R.layout.item_message_left,parent,false))
            MessageAdapter.MSG_TYPE_FROM_USER -> RightViewHolder(mInflater.inflate(R.layout.item_message_right,parent,false))
            else -> LeftViewHolder(mInflater.inflate(R.layout.item_message_left,parent,false))
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = mList[position]
        if (msg.type == MessageAdapter.MSG_TYPE_FROM_USER){
            val right = holder as RightViewHolder
            right.rightNameText.text = msg.name
            right.rightContentText.text = msg.content
        }else{
            val left = holder as LeftViewHolder
            left.leftNameText.text = msg.name
            left.leftContentText.text = msg.content
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    //根据消息的属性返回不同的viewType
    override fun getItemViewType(position:Int): Int {
        return mList[position].type
    }


    class LeftViewHolder(mView: View): RecyclerView.ViewHolder(mView){
        val leftImageView: CustomImageView = mView.findViewById(R.id.iv_msg_left)
        val leftNameText: TextView = mView.findViewById(R.id.tv_name_msg_left)
        val leftContentText: TextView = mView.findViewById(R.id.tv_content_msg_left)
    }

    class RightViewHolder(mView: View): RecyclerView.ViewHolder(mView){
        val rightImageView: CustomImageView = mView.findViewById(R.id.iv_msg_right)
        val rightNameText: TextView = mView.findViewById(R.id.tv_name_msg_right)
        val rightContentText: TextView = mView.findViewById(R.id.tv_content_msg_right)
    }


}