package com.jcodonuts.app.ui.mainHome

import android.net.Uri
import android.os.Bundle
import androidx.navigation.Navigation
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainHomeBinding
import com.jcodonuts.app.ui.MainActivity
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class HomeFragment @Inject constructor() : BaseFragment<FragmentMainHomeBinding, HomeViewModel>() {

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_home
    }

    override fun onViewReady(savedInstance: Bundle?) {

        binding.btnLogin.setOnClickListener {

            val uri = Uri.parse(getString(R.string.linkLogin))
            Navigation.findNavController((activity as MainActivity), R.id.nav_host_fragment)
                .navigate(uri)
        }
    }
}