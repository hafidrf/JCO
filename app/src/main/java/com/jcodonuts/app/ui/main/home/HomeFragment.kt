package com.jcodonuts.app.ui.main.home

import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.airbnb.epoxy.Carousel
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainHomeBinding
import com.jcodonuts.app.ui.MainActivity
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.Converter
import javax.inject.Inject

class
HomeFragment @Inject constructor() : BaseFragment<FragmentMainHomeBinding, HomeViewModel>() {
    private val TAG = "HomeFragment"

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_home
    }

    override fun onViewReady(savedInstance: Bundle?) {
        initRecyclerview()
        initObserver()

        if(!isFragmentFromPaused){
            viewModel.fetchHome()
        }
        viewModel.checkFromLogin()
    }

    private fun initRecyclerview(){
        val controller = HomeController(viewModel)
        controller.spanCount=2
        Carousel.setDefaultGlobalSnapHelperFactory(null)
        binding.homeRecyclerview.setController(controller)
        binding.homeRecyclerview.addItemDecoration(HomeSpacingDecoration())
        viewModel.datas.observe(this, Observer {
            controller.data = it
            binding.refresh.isRefreshing = false
        })

        viewModel.menuSelected.observe(this, {
            controller.menuSelected = it
        })

        binding.refresh.setOnRefreshListener {
            viewModel.fetchHomeFromAPI()
        }
    }

    private fun initObserver(){
        viewModel.showDialogCannotOrder.observe(this, {
            it.getContentIfNotHandled()?.let {

                val dlg = DialogCannotOrder()
                dlg.showDialog(requireActivity().supportFragmentManager, "HomeFragment", object : DialogCannotOrder.OnDialogClickListener {
                    override fun onLoginClick() {
                        dlg.dissmissDialog()
                        navigateTo(R.string.linkLogin)
                    }

                    override fun onRegisterClick() {
                        dlg.dissmissDialog()
                        navigateTo(R.string.linkRegister)
                    }
                })
            }
        })

        viewModel.showLinkaja.observe(this, {
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkLinkaja)
            }
        })

        viewModel.openProductDetail.observe(this, {
            it.getContentIfNotHandled()?.let {data->
                val url = getString(R.string.linkProductDetail).replace("{id}", data.menuCode)
                val uri = Uri.parse(url)
                navigateTo(uri)
            }
        })

        viewModel.showQRcode.observe(this, {
            it.getContentIfNotHandled()?.let {
                val dlg = DialogQrCode()
                dlg.showDialog(requireActivity().supportFragmentManager)
            }
        })

        viewModel.showPickup.observe(this, {
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkPickupFragment)
            }
        })

        viewModel.showMenuSearch.observe(this, {
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkMenuSearchFragment)
            }
        })

        viewModel.showChangeApp.observe(this, {
            it.getContentIfNotHandled()?.let {
                val dlg = DialogChangeApp()
                dlg.showDialog(requireActivity().supportFragmentManager, "HomeFragmentChangeApp", object : DialogChangeApp.OnDialogClickListener {
                    override fun onChange() {
                        dlg.dissmissDialog()
                    }
                })
            }
        })

        viewModel.showHotPromo.observe(this, {
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkHotPromoFragment)
            }
        })
    }

    override fun onBackPress() {
        requireActivity().finish()
    }
}