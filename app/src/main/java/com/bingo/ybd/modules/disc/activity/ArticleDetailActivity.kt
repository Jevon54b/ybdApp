package com.bingo.ybd.modules.disc.activity

import android.text.InputType
import android.util.Log
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.ext.successToast
import com.bingo.ybd.modules.disc.custom.CommentAdpter
import com.bingo.ybd.modules.disc.model.CommentModel
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_article_detail.*



class ArticleDetailActivity:BaseVMActivity(){

    val TAG  = "ArticleDetailActivity"

    lateinit var commentList:List<CommentModel>

    override fun getLayoutId(): Int {
        return R.layout.activity_article_detail
    }

    fun RecyclerView.matchHeight(itemNum:Int){
//        if (itemNum!=0){
//            val itemHeight = resources.getDimension(R.dimen)
//        }
    }

    override fun initView() {
        initDatas()
        var adpter = CommentAdpter(this,commentList)
        recyclerView.adapter = adpter
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
                positiveButton(R.string.comment_publish)
                negativeButton(R.string.comment_cancel)
                lifecycleOwner(this@ArticleDetailActivity)
            }
        }
    }

    fun initDatas(){
        var json = "[\n" +
                "        {\n" +
                "            \"id\": \"1\",\n" +
                "            \"article_id\": \"2\",\n" +
                "            \"commenter\": \"啊哈哈\",\n" +
                "            \"content\": \"哈哈哈\",\n" +
                "            \"realease_time\": 1577515400000,\n" +
                "            \"commenter_id\": \"5\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"2\",\n" +
                "            \"article_id\": \"2\",\n" +
                "            \"commenter\": \"嘻嘻嘻嘻\",\n" +
                "            \"content\": \"嗯嗯嗯呃\",\n" +
                "            \"realease_time\": 1577515436000,\n" +
                "            \"commenter_id\": \"1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"13\",\n" +
                "            \"article_id\": \"2\",\n" +
                "            \"commenter\": \"cx\",\n" +
                "            \"content\": \"谢谢陈医生\",\n" +
                "            \"realease_time\": 1577589952000,\n" +
                "            \"commenter_id\": \"9\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"23\",\n" +
                "            \"article_id\": \"2\",\n" +
                "            \"commenter\": \"1\",\n" +
                "            \"content\": \"嗯嗯\",\n" +
                "            \"realease_time\": 1589811778000,\n" +
                "            \"commenter_id\": \"1\"\n" +
                "        },\n" +
                "           {\n" +
                    "            \"id\": \"23\",\n" +
                            "            \"article_id\": \"2\",\n" +
                            "            \"commenter\": \"1\",\n" +
                            "            \"content\": \"嗯嗯\",\n" +
                            "            \"realease_time\": 1589811778000,\n" +
                            "            \"commenter_id\": \"1\"\n" +
                            "        }\n" +
                "    ]"
        var gson = Gson()
        commentList = gson.fromJson(json,object:TypeToken<List<CommentModel>>(){}.type)
    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }
}