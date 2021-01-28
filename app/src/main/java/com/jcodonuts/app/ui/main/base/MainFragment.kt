package com.jcodonuts.app.ui.main.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.setupWithNavControllerCustom
import javax.inject.Inject

class MainFragment @Inject constructor() : BaseFragment<FragmentMainBinding, MainViewModel>() {
    private val TAG = "MainFragment"

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewReady(savedInstance: Bundle?) {
        setUpNavigation()
        initObserver()
        viewModel.loginCheck()
    }

    private fun setUpNavigation(){
        val navHostFragment = childFragmentManager.findFragmentById(R.id.navHostFragmentMain) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnNavigationItemSelectedListener {item->
            NavigationUI.onNavDestinationSelected(item, navController)
        }
        binding.bottomNavigation.setOnNavigationItemReselectedListener {

        }

        arguments?.let {
            if(it.getString("menu").equals("cart")){
                binding.bottomNavigation.selectedItemId = R.id.cart
            }
        }
    }

    private fun initObserver(){
        viewModel.showBottomNavigation.observe(this, {
            it.getContentIfNotHandled()?.let {
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        })
        viewModel.hideBottomNavigation.observe(this, {
            it.getContentIfNotHandled()?.let {
                binding.bottomNavigation.visibility = View.GONE
            }
        })
    }

    fun backToHome(){
        binding.bottomNavigation.selectedItemId = R.id.home
    }
}