package com.jcodonuts.app.ui.payment.topup

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentPaymentTopupBinding
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class TopupFragment @Inject constructor() : BaseFragment<FragmentPaymentTopupBinding, TopupViewModel>() {

    override fun getViewModelClass(): Class<TopupViewModel> {
        return TopupViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_payment_topup
    }

    override fun onViewReady(savedInstance: Bundle?) {
        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }

        val controller = TopupController()
        binding.recyclerview.setController(controller)

        if(!isFragmentFromPaused){
            viewModel.loadData()
        }

        viewModel.datas.observe(this, {
            controller.data = it
        })
    }
}