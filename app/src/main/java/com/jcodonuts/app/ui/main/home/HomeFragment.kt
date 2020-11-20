package com.jcodonuts.app.ui.main.home

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.airbnb.epoxy.Carousel
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainHomeBinding
import com.jcodonuts.app.ui.MainActivity
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.main.base.MainFragment
import com.jcodonuts.app.utils.DialogCannotOrder
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
        controller.spanCount=2
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

        viewModel.showDialogCannotOrder.observe(this, {
            it.getContentIfNotHandled()?.let {
                Log.d(TAG, "showDialogCannotOrder: ")

                val dlg = DialogCannotOrder()
                dlg.showDialog(requireActivity().supportFragmentManager, "HomeFragment", object : DialogCannotOrder.OnDialogClickListener {
                    override fun onLoginClick() {
                        dlg.dissmissDialog()
                        val uri = Uri.parse(getString(R.string.linkLogin))
                        Navigation.findNavController((activity as MainActivity), R.id.nav_host_fragment)
                                .navigate(uri)
                    }

                    override fun onRegisterClick() {
                        dlg.dissmissDialog()
                        val uri = Uri.parse(getString(R.string.linkRegister))
                        Navigation.findNavController((activity as MainActivity), R.id.nav_host_fragment)
                                .navigate(uri)
                    }
                })
            }
        })

//        binding.btnForgot.setOnClickListener {
//            val uri = Uri.parse(getString(R.string.linkForgot))
//            Navigation.findNavController((activity as MainActivity), R.id.nav_host_fragment)
//                .navigate(uri)
//        }
    }

    override fun onBackPress() {
        requireActivity().finish()
    }
}