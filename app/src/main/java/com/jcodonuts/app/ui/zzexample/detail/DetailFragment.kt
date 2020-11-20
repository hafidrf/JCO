package com.jcodonuts.app.ui.zzexample.detail

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentDetailBinding
import com.jcodonuts.app.ui.MainActivity
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class DetailFragment @Inject constructor() : BaseFragment<FragmentDetailBinding, DetailViewModel>(){

    private val TAG = "MainFragment"
    private val args : DetailFragmentArgs by navArgs()

    override fun getViewModelClass(): Class<DetailViewModel> {
        return DetailViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun onViewReady(savedInstance: Bundle?) {

        binding.viewModel = viewModel
        load()

        (activity as MainActivity).setSupportActionBar(binding.toolbar)

        binding.lytOffline.btnRetry.setOnClickListener {
            load()
        }
    }

    private fun load(){
        viewModel.loadMenu(args.urlWeb, binding.webView)
    }
}