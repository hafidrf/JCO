package com.jcodonuts.app.ui.mainHome

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainCartBinding
import com.jcodonuts.app.databinding.FragmentMainHomeBinding
import com.jcodonuts.app.ui.MainActivity
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.splash.SplashFragmentDirections
import javax.inject.Inject

class HomeFragment @Inject constructor() : BaseFragment<FragmentMainHomeBinding, HomeViewModel>() {

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_home
    }

    override fun onViewReady(savedInstance: Bundle?) {

//        binding.btnTest.setOnClickListener {
//
//            val uri = Uri.parse("jcodonuts://com.jcodonuts.app/article")
//            Navigation.findNavController((activity as MainActivity), R.id.nav_host_fragment)
//                .navigate(uri)
//        }
    }
}