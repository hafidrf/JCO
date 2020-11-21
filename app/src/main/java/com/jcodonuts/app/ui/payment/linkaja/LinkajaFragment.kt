package com.jcodonuts.app.ui.payment.linkaja

import android.os.Bundle
import com.airbnb.epoxy.Carousel
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentPaymentLinkajaBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.main.home.HomeController
import javax.inject.Inject

class LinkajaFragment @Inject constructor() : BaseFragment<FragmentPaymentLinkajaBinding, LinkajaViewModel>() {

    override fun getViewModelClass(): Class<LinkajaViewModel> {
        return LinkajaViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_payment_linkaja
    }

    override fun getStatusBarColor(): Int {
        return R.color.colorAccent
    }

    override fun onViewReady(savedInstance: Bundle?) {
        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }

        val controller = LinkajaController(viewModel)
        binding.linkajaRecyclerview.setController(controller)

        if(!isFragmentFromPaused){
            viewModel.loadData()
        }

        viewModel.datas.observe(this, {
            controller.data = it
        })

        viewModel.topupTypeClick.observe(this, {
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkTopup)
            }
        })
    }
}