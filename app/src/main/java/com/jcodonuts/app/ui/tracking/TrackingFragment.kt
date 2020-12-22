package com.jcodonuts.app.ui.tracking

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentPaymentTopupBinding
import com.jcodonuts.app.databinding.FragmentTrackingBinding
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class TrackingFragment @Inject constructor() : BaseFragment<FragmentTrackingBinding, TrackingViewModel>() {

    override fun getViewModelClass(): Class<TrackingViewModel> {
        return TrackingViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tracking
    }

    override fun onViewReady(savedInstance: Bundle?) {
        setupActionBar()

        val controller = TrackingController()
        binding.recyclerView.setController(controller)

        if(!isFragmentFromPaused){
            viewModel.loadData()
        }

        viewModel.datas.observe(this, {
            controller.data = it
        })
    }

    private fun setupActionBar(){
        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }
    }
}