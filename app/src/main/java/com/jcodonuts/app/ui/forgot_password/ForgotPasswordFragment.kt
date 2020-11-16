package com.jcodonuts.app.ui.forgot_password

import android.os.Bundle
import android.util.Log
import android.view.View
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentForgotPasswordBinding
import com.jcodonuts.app.databinding.FragmentLoginBinding
import com.jcodonuts.app.databinding.FragmentMainCartBinding
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class ForgotPasswordFragment @Inject constructor() : BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>() {

    private val TAG = "ForgotPasswordFragment"

    override fun getViewModelClass(): Class<ForgotPasswordViewModel> {
        return ForgotPasswordViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_forgot_password

    }

    override fun onViewReady(savedInstance: Bundle?) {
        binding.btnBack.setOnClickListener {
            onBackPress()
        }

        binding.btnSend.setOnClickListener {
            binding.lytInputPhone.visibility = View.GONE
            binding.lytOTP.visibility = View.VISIBLE
        }

        binding.otpView.setOtpCompletionListener {
            Log.d(TAG, it);
        }
    }
}