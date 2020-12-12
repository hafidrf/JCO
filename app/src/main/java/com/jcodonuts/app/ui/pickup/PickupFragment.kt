package com.jcodonuts.app.ui.pickup

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.Carousel
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.Divider16
import com.jcodonuts.app.databinding.FragmentMainCartBinding
import com.jcodonuts.app.databinding.FragmentPickupBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.base.InjectingNavHostFragment
import com.jcodonuts.app.ui.main.base.MainFragment
import com.jcodonuts.app.ui.main.home.HomeController
import com.jcodonuts.app.ui.main.home.HomeSpacingDecoration
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