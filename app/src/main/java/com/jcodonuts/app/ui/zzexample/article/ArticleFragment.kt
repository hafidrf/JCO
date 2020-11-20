package com.jcodonuts.app.ui.zzexample.article

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.Country
import com.jcodonuts.app.data.remote.model.Article
import com.jcodonuts.app.databinding.FragmentArticleBinding
import com.jcodonuts.app.ui.MainActivity
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.OnScrollListener
import javax.inject.Inject

class ArticleFragment @Inject constructor() : BaseFragment<FragmentArticleBinding, ArticleViewModel>(),
MenuAdapter.OnMenuItemClickListener{

    private val TAG = "MainFragment"
    private lateinit var adapter:MenuAdapter
    private lateinit var articleAdapter : ArticleAdapter

    private lateinit var onScrollListener: OnScrollListener

    override fun getViewModelClass(): Class<ArticleViewModel> {
        return ArticleViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_article
    }

    override fun onViewReady(savedInstance: Bundle?) {

        binding.viewModel = viewModel

        initToolbar()
        initMenuRecyclerview()
        initArticleRecyclerview()
        initErrorView()

        if(!isFragmentFromPaused){
            context?.let {viewModel.loadMenu(it)}
        }
    }

    private fun initToolbar(){
        (activity as MainActivity).setSupportActionBar(binding.content.toolbar)
        binding.content.toolbar.setNavigationOnClickListener(View.OnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        })
        viewModel.country.observe(this, Observer {
            binding.content.toolbar.subtitle = it.name
        })
    }

    private fun initArticleRecyclerview(){
        onScrollListener = OnScrollListener(binding.content.recyclerview.layoutManager as LinearLayoutManager) {
            viewModel.loadMore()
        }

        articleAdapter = ArticleAdapter(object : ArticleAdapter.OnArticleItemClickListener{
            override fun onClick(article: Article) {
                val action = ArticleFragmentDirections.actionMainFragmentToDetailFragment(
                    article.url
                )
                findNavController()
                    .navigate(
                        action,
                        FragmentNavigator.Extras.Builder()
                            .build()
                    )
            }
        })
        binding.content.recyclerview.adapter = articleAdapter
        binding.content.recyclerview.addOnScrollListener(onScrollListener)

        viewModel.article.observe(this, Observer {
            Log.d(TAG, "size: "+it.size);
            articleAdapter.submitList(it)
        })
    }

    private fun initMenuRecyclerview() {
        adapter = MenuAdapter(this)
        binding.rcMenu.adapter = adapter

        viewModel.countries.observe(this, Observer {
            adapter.submitList(it)
            if(!isFragmentFromPaused){
                viewModel.loadNews(it[18])
            }
        })
    }

    override fun onClick(country: Country) {
        binding.drawerLayout.closeDrawer(GravityCompat.START)

        Log.d(TAG, ": "+country.code)
        onScrollListener.reset()
        viewModel.loadNews(country)
    }

    private fun initErrorView(){
        binding.content.lytOffline.btnRetry.setOnClickListener {
            onScrollListener.reset()
            viewModel.retry()
        }
    }
}