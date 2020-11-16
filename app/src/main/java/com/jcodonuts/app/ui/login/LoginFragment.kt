package com.jcodonuts.app.ui.login

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentLoginBinding
import com.jcodonuts.app.databinding.FragmentMainCartBinding
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class LoginFragment @Inject constructor() : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override fun getViewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login

    }

    override fun onViewReady(savedInstance: Bundle?) {

    }
}