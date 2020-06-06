package com.bingo.ybd.modules.disc.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bingo.ybd.R
import com.bingo.ybd.data.repository.Repository
import com.bingo.ybd.modules.disc.model.CommentModel
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*


class CommentAdpter(val mContext:Context,val commentList:List<CommentModel>): RecyclerView.Adapter<CommentAdpter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_comment,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data:CommentModel = commentList[position]
        Glide.with(mContext).load(mContext.resources.getDrawable(Repository.getUserPhoto())).into(holder.commenterImg)
        holder.commentText.setText(data.content)
        holder.commenterNameText.setText(data.commenterName)
        var time = Date()
        time.time = data.releaseTime.toLong()
        val str = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time)
        holder.commentTimeText.setText(str)
    }

    class ViewHolder(mView:View):RecyclerView.ViewHolder(mView){
        val commenterImg:ImageView = mView.findViewById(R.id.commenterImg)
        val commenterNameText:TextView = mView.findViewById(R.id.commenterNameText)
        val commentTimeText:TextView = mView.findViewById(R.id.commentTimeText)
        val commentText:TextView = mView.findViewById(R.id.commentText)
    }
}