package com.bingo.ybd.modules.disc.fragment


import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bingo.ybd.R
import com.bingo.ybd.base.fragment.BaseVMFragment
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.base.viewmodel.SuccessState
import com.bingo.ybd.data.model.Article
import com.bingo.ybd.modules.disc.custom.ArticleAdapter
import com.bingo.ybd.modules.disc.vm.DiscViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_disc.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscFragment : BaseVMFragment() {

    lateinit var articleList: List<Article>
    private val discViewModel : DiscViewModel by viewModel()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_disc
    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

    override fun initView() {
        var adapter = ArticleAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun initData() {
        discViewModel.getArticleList().observe(this, Observer {
            when(it.status){
                SuccessState
            }
        })
    }
}