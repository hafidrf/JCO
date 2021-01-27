package com.jcodonuts.app.ui.auth.login

import android.os.Bundle
import android.view.View
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentLoginBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.DialogError
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
        binding.viewModel = viewModel
        binding.executePendingBindings()

        KeyboardUtil(requireActivity(), binding.root)
        binding.btnForgotPassword.setOnClickListener {
            navigateTo(R.string.linkForgot)
        }

        binding.btnRegister.setOnClickListener {
            navigateTo(R.string.linkRegister)
        }

        binding.btnLogin.setOnClickListener {
            binding.edtPhoneNo.error = null
            binding.edtPassword.error = null
            viewModel.login(binding.edtPhoneNo.editText?.text.toString(), binding.edtPassword.editText?.text.toString())
        }

        initObserver()
    }

    private fun initObserver(){
        viewModel.errorPhone.observe(this, {
            it.getContentIfNotHandled()?.let {error->
                binding.edtPhoneNo.error = error
            }
        })
        viewModel.errorPassword.observe(this, {
            it.getContentIfNotHandled()?.let {error->
                binding.edtPassword.error = error
            }
        })

        viewModel.closeLoginPage.observe(this, {
            it.getContentIfNotHandled()?.let {
                onBackPress()
            }
        })

        viewModel.loginFailed.observe(this, {
            it.getContentIfNotHandled()?.let {message->
                val dlg = DialogError(requireContext())
                dlg.showPopup(
                    message,
                    View.OnClickListener{
                        dlg.dismissPopup()
                    })
            }
        })
    }
}