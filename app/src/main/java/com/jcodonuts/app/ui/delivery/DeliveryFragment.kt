package com.jcodonuts.app.ui.delivery

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentDeliveryBinding
import com.jcodonuts.app.databinding.FragmentPickupBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.main.menu_search.MenuSearchController
import javax.inject.Inject

class DeliveryFragment @Inject constructor() : BaseFragment<FragmentDeliveryBinding, DeliveryViewModel>() {

    override fun getViewModelClass(): Class<DeliveryViewModel> {
        return DeliveryViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_delivery
    }

    override fun onViewReady(savedInstance: Bundle?) {
        initActionBar()
        initRecyclerview()

        if(!isFragmentFromPaused){
            viewModel.loadLocations()
        }
    }

    private fun initActionBar(){
        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }
    }

    private fun initRecyclerview(){
        val controller = DeliveryController(viewModel)
        binding.recyclerview.setController(controller)
        viewModel.datas.observe(this,  {
            controller.data = it
        })
    }
}