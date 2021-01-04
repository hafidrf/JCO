package com.jcodonuts.app.ui.main.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.observe
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainProfileBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.base.InjectingNavHostFragment
import com.jcodonuts.app.ui.main.base.MainFragment
import javax.inject.Inject

class ProfileFragment @Inject constructor() : BaseFragment<FragmentMainProfileBinding, ProfileViewModel>() {
    private val TAG = "ProfileFragment"

    override fun getViewModelClass(): Class<ProfileViewModel> {
        return ProfileViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_profile
    }

    override fun onViewReady(savedInstance: Bundle?) {
        initRecyclerview()
        initObserver()

        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }

        if(!isFragmentFromPaused){
            viewModel.loadData()
        }
    }

    private fun initRecyclerview(){
        val controller = ProfileController(viewModel)
        binding.recyclerview.setController(controller)
        viewModel.datas.observe(this,  {
            controller.setData(it)
        })
    }

    private fun initObserver(){
        viewModel.showEditProfile.observe(this,{
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkEditProfileFragment)
            }
        })

        viewModel.showMenuDetail.observe(this, {
            it.getContentIfNotHandled()?.let{index->
                when (index) {
                    ProfileViewModel.CHANGE_PASSWORD -> {
                        navigateTo(R.string.linkChangePasswordFragment)
                    }
                    ProfileViewModel.ORDER ->{
//                        navigateTo(R.string.linkChangePasswordFragment)
                    }
                }
            }
        })
    }

    override fun onBackPress() {
        val navhost = (parentFragment as InjectingNavHostFragment)
        (navhost.parentFragment as MainFragment).backToHome()
    }
}