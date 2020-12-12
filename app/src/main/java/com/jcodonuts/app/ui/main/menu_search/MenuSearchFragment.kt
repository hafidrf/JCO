package com.jcodonuts.app.ui.main.menu_search

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMenuSearchBinding
import com.jcodonuts.app.databinding.FragmentPickupBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.main.home.HomeSpacingDecoration
import javax.inject.Inject

class MenuSearchFragment @Inject constructor() : BaseFragment<FragmentMenuSearchBinding, MenuSearchViewModel>() {

    override fun getViewModelClass(): Class<MenuSearchViewModel> {
        return MenuSearchViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_menu_search
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
        val controller = MenuSearchController(viewModel)
        binding.recyclerview.setController(controller)
        binding.recyclerview.addItemDecoration(HomeSpacingDecoration())
        viewModel.datas.observe(this,  {
            controller.data = it
        })
    }
}