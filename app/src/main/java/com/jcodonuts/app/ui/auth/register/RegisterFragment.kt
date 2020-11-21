package com.jcodonuts.app.ui.auth.register

import android.os.Bundle
import android.view.View
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentRegisterBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.DialogJco
import javax.inject.Inject

class RegisterFragment @Inject constructor() : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {
    private val TAG = "LoginFragment"

    override fun getViewModelClass(): Class<RegisterViewModel> {
        return RegisterViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_register

    }

    override fun onViewReady(savedInstance: Bundle?) {
        binding.btnBack.setOnClickListener {
            onBackPress()
        }

        binding.btnNext.setOnClickListener {
            binding.lytInputPhone.visibility = View.GONE
            binding.lytOTP.visibility = View.VISIBLE
        }

        binding.btnVerification.setOnClickListener {
            binding.lytOTP.visibility = View.GONE
            binding.lytRegister.visibility = View.VISIBLE
        }

        binding.btnCreate.setOnClickListener {
            val dialog = DialogJco(requireContext())
            dialog.showPopup("Your account has been created",
            "Please login to place an order", "Login"
            ) {
                dialog.dismissPopup();
                onBackPress()
            }
        }


    }
}