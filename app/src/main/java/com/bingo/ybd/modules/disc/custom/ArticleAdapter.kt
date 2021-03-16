package com.bingo.ybd.modules.disc.custom

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
import com.bingo.ybd.data.model.Article
import com.bingo.ybd.modules.disc.activity.ArticleDetailActivity
import com.bingo.ybd.util.StringUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article.view.*
import java.text.SimpleDateFormat
import java.util.*

class ArticleAdapter(
    private val mContext: Context
) : BaseRecyclerAdapter<Article, ArticleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        Glide.with(mContext).load(data.pic).into(holder.picImg)
        holder.titleText.text = data.title
        holder.authorText.text = data.author
        holder.commentNumText.text = data.commentNum.toString()
        holder.visitNumText.text = data.readNum.toString()
        var formatTime = StringUtils.convertTimeStampToFormat(data.releaseTime)
        holder.timeText.text = formatTime
        holder.itemView.setOnClickListener {
            var intent = Intent(mContext,ArticleDetailActivity::class.java)
            intent.putExtra(Constant.KEY_ARTICLE_ID,data.id)
            mContext.startActivity(intent)
        }
    }


    class ViewHolder(mView:View):RecyclerView.ViewHolder(mView){
        val picImg:ImageView = mView.findViewById(R.id.articleImg)
        val titleText:TextView = mView.findViewById(R.id.articleTitleText)
        val authorText:TextView  = mView.findViewById(R.id.authorNameText)
        val timeText:TextView = mView.findViewById(R.id.articleTimeText)
        val visitNumText:TextView = mView.findViewById(R.id.visitNumText)
        val commentNumText:TextView = mView.findViewById(R.id.commentNumText)
    }
}