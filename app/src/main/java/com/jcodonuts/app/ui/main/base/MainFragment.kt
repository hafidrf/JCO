package com.jcodonuts.app.ui.main.base

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainBinding
import com.jcodonuts.app.ui.base.BaseFragment
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
    }

    fun backToHome(){
        binding.bottomNavigation.selectedItemId = R.id.home
    }
}