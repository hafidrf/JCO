package com.jcodonuts.app.ui.payment.payment_detail

import android.os.Bundle
import com.airbnb.epoxy.Carousel
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentChoosePaymentBinding
import com.jcodonuts.app.databinding.FragmentPaymentDetailBinding
import com.jcodonuts.app.databinding.FragmentPaymentLinkajaBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.main.home.HomeController
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

//        viewModel.paymentClick.observe(this, {
//            it.getContentIfNotHandled()?.let {
//                navigateTo(R.string.linkTopup)
//            }
//        })
    }

    private fun setupActionBar(){
        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }
    }
}