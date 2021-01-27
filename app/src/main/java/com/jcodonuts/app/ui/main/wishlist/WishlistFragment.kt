package com.jcodonuts.app.ui.main.wishlist

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainWishlistBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.base.InjectingNavHostFragment
import com.jcodonuts.app.ui.main.base.MainFragment
import com.jcodonuts.app.ui.main.home.HomeSpacingDecoration
import javax.inject.Inject

class WishlistFragment @Inject constructor() : BaseFragment<FragmentMainWishlistBinding, WishlistViewModel>() {

    override fun getViewModelClass(): Class<WishlistViewModel> {
        return WishlistViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_wishlist
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
        val controller = WishlistController(viewModel)
        binding.recyclerview.setController(controller)
        binding.recyclerview.addItemDecoration(HomeSpacingDecoration())
        viewModel.datas.observe(this,  {
            controller.data = it
        })
    }

    override fun onBackPress() {
        val navhost = (parentFragment as InjectingNavHostFragment)
        (navhost.parentFragment as MainFragment).backToHome()
    }
}