package com.bingo.ybd.modules.disc.activity

import android.content.Intent
import android.text.InputType
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.config.Settings
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.data.model.Comment
import com.bingo.ybd.ext.successToast
import com.bingo.ybd.modules.disc.custom.CommentAdapter
import com.bingo.ybd.modules.disc.vm.DiscViewModel
import com.bingo.ybd.util.StringUtils
import com.bumptech.glide.Glide
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_article_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ArticleDetailActivity:BaseVMActivity(){

    companion object{
        private const val TAG  = "ArticleDetailActivity"

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_article_detail
    }

    override fun getViewModel(): BaseViewModel {
        return discViewModel
    }

    private val discViewModel : DiscViewModel by viewModel()

    private lateinit var commentAdapter: CommentAdapter

    private var articleId: String = "0"

    fun RecyclerView.matchHeight(itemNum:Int){
//        if (itemNum!=0){
//            val itemHeight = resources.getDimension(R.dimen)
//        }
    }

    override fun initView() {
        commentAdapter = CommentAdapter(this)
        recyclerView.adapter = commentAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        this.dividerBuilder()
            .insets(58,58)
            .build()
            .addTo(recyclerView)

        addCommentText.setOnClickListener {
            MaterialDialog(this, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
                input(
                    hint = "输入评论...",
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
                ) { _, text ->
                    successToast("Input: $text")
                }
                positiveButton(R.string.comment_publish){
                    discViewModel.addComment(articleId.toInt(),Settings.Account.userId.toInt(),it.getInputField().text.toString(),Settings.Account.userName)
                }
                negativeButton(R.string.comment_cancel)
                lifecycleOwner(this@ArticleDetailActivity)
            }
        }
    }

    override fun initData() {
        articleId = intent?.getStringExtra(Constant.KEY_ARTICLE_ID)?:"0"
        discViewModel.getArticleDetail(articleId.toInt()).observe(this, Observer {
            val article = it.data
            articleTitleText.text = article.title
            authorNameText.text = article.author
            publishTimeText.text = StringUtils.convertTimeStampToFormat(article.releaseTime)
            Glide.with(this).load(article.pic).into(articleImg)
            contentText.text = article.content
        })
        discViewModel.commentLiveData.observe(this, Observer{
            commentAdapter.replaceAll(it)
            commentAdapter.notifyDataSetChanged()
        })
        discViewModel.getArticleCommentList(articleId.toInt())
    }


}