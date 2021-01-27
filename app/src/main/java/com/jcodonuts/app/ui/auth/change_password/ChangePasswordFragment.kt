package com.jcodonuts.app.ui.auth.change_password

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentChangePasswordBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.KeyboardUtil
import javax.inject.Inject

class ChangePasswordFragment @Inject constructor(): BaseFragment<FragmentChangePasswordBinding, ChangePasswordViewModel>() {

    override fun getViewModelClass(): Class<ChangePasswordViewModel> {
        return ChangePasswordViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_change_password
    }

    override fun onViewReady(savedInstance: Bundle?) {
        KeyboardUtil(requireActivity(), binding.root)
    }
}