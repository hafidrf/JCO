package com.jcodonuts.app.ui.auth.login

import android.os.Bundle
import android.util.Log
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentLoginBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.KeyboardUtil
import javax.inject.Inject

class LoginFragment @Inject constructor() : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    private val TAG = "LoginFragment"

    override fun getViewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login

    }

    override fun onViewReady(savedInstance: Bundle?) {
        KeyboardUtil(requireActivity(), binding.root)
        binding.btnForgotPassword.setOnClickListener {
            navigateTo(R.string.linkForgot)
        }

        binding.btnRegister.setOnClickListener {
            navigateTo(R.string.linkRegister)
        }
    }
}