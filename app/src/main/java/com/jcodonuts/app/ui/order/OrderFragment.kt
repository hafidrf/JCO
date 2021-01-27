package com.jcodonuts.app.ui.order

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentOrderBinding
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class OrderFragment @Inject constructor() : BaseFragment<FragmentOrderBinding, OrderViewModel>() {

    override fun getViewModelClass(): Class<OrderViewModel> {
        return OrderViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_order
    }

    override fun onViewReady(savedInstance: Bundle?) {
        setupActionBar()
        setupRecyclerview()
    }

    private fun setupRecyclerview(){
        val controller = OrderController(viewModel)
        binding.recyclerview.setController(controller)

        if(!isFragmentFromPaused){
            viewModel.loadData()
        }

        viewModel.datas.observe(this, {
            controller.data = it
        })

        viewModel.orderClick.observe(this, {
            it.getContentIfNotHandled()?.let {
//                navigateTo(R.string.linkTopup)
            }
        })
    }

    private fun setupActionBar(){
        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }
    }
}