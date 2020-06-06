package com.bingo.ybd.modules.main.custom

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bingo.ybd.modules.main.model.MedBriefModel
import com.bumptech.glide.Glide
import com.youth.banner.adapter.BannerAdapter

class ImageBannerAdapter(private val mContext:Context, datas:List<MedBriefModel>):BannerAdapter<MedBriefModel,ImageBannerAdapter.ViewHolder>(datas){

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var imageView = ImageView(parent?.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return ViewHolder(imageView)
    }

    override fun onBindView(
        holder: ViewHolder?,
        data: MedBriefModel?,
        position: Int,
        size: Int
    ) {
        Glide.with(mContext).load(data?.pic).into(holder!!.imageView)
    }

    class ViewHolder(val imageView:ImageView):RecyclerView.ViewHolder(imageView)
}
