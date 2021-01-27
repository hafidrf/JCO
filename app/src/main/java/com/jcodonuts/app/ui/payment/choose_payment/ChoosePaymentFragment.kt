package com.jcodonuts.app.ui.payment.choose_payment

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentChoosePaymentBinding
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class ChoosePaymentFragment @Inject constructor() : BaseFragment<FragmentChoosePaymentBinding, ChoosePaymentViewModel>() {

    override fun getViewModelClass(): Class<ChoosePaymentViewModel> {
        return ChoosePaymentViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_choose_payment
    }

    override fun onViewReady(savedInstance: Bundle?) {
        setupActionBar()
        setupRecyclerview()
    }

    private fun setupRecyclerview(){
        val controller = ChoosePaymentController(viewModel)
        binding.recyclerview.setController(controller)

        if(!isFragmentFromPaused){
            viewModel.loadData()
        }

        viewModel.datas.observe(this, {
            controller.data = it
        })

        viewModel.paymentClick.observe(this, {
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkTopup)
            }
        })
    }

    private fun setupActionBar(){
        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }
    }
}