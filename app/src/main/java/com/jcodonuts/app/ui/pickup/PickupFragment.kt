package com.jcodonuts.app.ui.pickup

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentPickupBinding
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class PickupFragment @Inject constructor() : BaseFragment<FragmentPickupBinding, PickupViewModel>() {

    override fun getViewModelClass(): Class<PickupViewModel> {
        return PickupViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_pickup
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
        val controller = PickupController(viewModel)
        binding.recyclerview.setController(controller)
        viewModel.datas.observe(this,  {
            controller.data = it
        })
    }
}