package com.jcodonuts.app.ui.main.profile

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainProfileBinding
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class ProfileFragment @Inject constructor() : BaseFragment<FragmentMainProfileBinding, ProfileViewModel>() {

    override fun getViewModelClass(): Class<ProfileViewModel> {
        return ProfileViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_profile
    }

    override fun onViewReady(savedInstance: Bundle?) {

    }
}