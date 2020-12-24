package com.jcodonuts.app.ui.hot_promo

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentHotPromoBinding
import com.jcodonuts.app.databinding.FragmentPaymentTopupBinding
import com.jcodonuts.app.databinding.FragmentTrackingBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.DialogJco
import javax.inject.Inject

class HotPromoFragment @Inject constructor() : BaseFragment<FragmentHotPromoBinding, HotPromoViewModel>() {

    override fun getViewModelClass(): Class<HotPromoViewModel> {
        return HotPromoViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_hot_promo
    }

    override fun onViewReady(savedInstance: Bundle?) {
        setupActionBar()
        initObserver()
        initRecycler()

//        binding.refresh.setOnRefreshListener {
//            Handler().postDelayed({ binding.refresh.isRefreshing = false },2000)
//        }


    }

    private fun setupActionBar(){
        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }
    }

    private fun initRecycler(){
        val controller = HotPromoController(viewModel)
        binding.recyclerView.setController(controller)

        if(!isFragmentFromPaused){
            viewModel.loadData()
        }

        viewModel.datas.observe(this, {
            controller.data = it
        })
    }

    private fun initObserver(){

    }
}