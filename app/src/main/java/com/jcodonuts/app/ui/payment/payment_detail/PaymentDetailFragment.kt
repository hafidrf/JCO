package com.jcodonuts.app.ui.payment.payment_detail

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentPaymentDetailBinding
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class PaymentDetailFragment @Inject constructor() : BaseFragment<FragmentPaymentDetailBinding, PaymentDetailViewModel>() {

    override fun getViewModelClass(): Class<PaymentDetailViewModel> {
        return PaymentDetailViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_payment_detail
    }

    override fun onViewReady(savedInstance: Bundle?) {
        setupActionBar()
        setupRecyclerview()
        setupObserver()
    }

    private fun setupRecyclerview(){
        val controller = PaymentDetailController(viewModel)
        binding.recyclerview.setController(controller)

        if(!isFragmentFromPaused){
            viewModel.loadData()
        }

        viewModel.datas.observe(this, {
            controller.data = it
        })

        viewModel.trackingClick.observe(this, {
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkTrackingFragment)
            }
        })
    }

    private fun setupObserver(){
        viewModel.trackingClick.observe(this, {
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkTrackingFragment)
            }
        })

        viewModel.orderAgainClick.observe(this, {
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkMainFragment)
            }
        })
    }

    private fun setupActionBar(){
        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }
    }
}