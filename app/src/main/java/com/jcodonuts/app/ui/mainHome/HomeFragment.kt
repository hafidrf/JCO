package com.jcodonuts.app.ui.mainHome

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.airbnb.epoxy.Carousel
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainHomeBinding
import com.jcodonuts.app.ui.MainActivity
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class HomeFragment @Inject constructor() : BaseFragment<FragmentMainHomeBinding, HomeViewModel>() {
    private val TAG = "HomeFragment"

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_home
    }

    override fun getStatusBarColor(): Int {
        return R.color.colorAccent
    }

    override fun onViewReady(savedInstance: Bundle?) {

        val controller = HomeController(viewModel)
        Carousel.setDefaultGlobalSnapHelperFactory(null)
        binding.homeRecyclerview.setController(controller)

        if(!isFragmentFromPaused){
            viewModel.loadPromo()
        }

        viewModel.datas.observe(this, Observer {
            controller.data = it
        })

        viewModel.menuSelected.observe(this, {
            controller.menuSelected = it
        })


//        binding.btnLogin.setOnClickListener {
//
//            val uri = Uri.parse(getString(R.string.linkLogin))
//            Navigation.findNavController((activity as MainActivity), R.id.nav_host_fragment)
//                .navigate(uri)
//        }
//        binding.btnForgot.setOnClickListener {
//
//            val uri = Uri.parse(getString(R.string.linkForgot))
//            Navigation.findNavController((activity as MainActivity), R.id.nav_host_fragment)
//                .navigate(uri)
//        }
//        binding.btnRegister.setOnClickListener {
//
//            val uri = Uri.parse(getString(R.string.linkRegister))
//            Navigation.findNavController((activity as MainActivity), R.id.nav_host_fragment)
//                .navigate(uri)
//        }
    }
}