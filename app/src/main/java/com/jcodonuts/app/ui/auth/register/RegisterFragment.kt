package com.jcodonuts.app.ui.auth.register

import android.os.Bundle
import android.view.View
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentRegisterBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.DialogJco
import com.jcodonuts.app.utils.KeyboardUtil
import javax.inject.Inject

class RegisterFragment @Inject constructor() : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {
    private val TAG = "LoginFragment"

    private lateinit var auth: FirebaseAuth

    override fun getViewModelClass(): Class<RegisterViewModel> {
        return RegisterViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_register

    }

    override fun onViewReady(savedInstance: Bundle?) {
        KeyboardUtil(requireActivity(), binding.root)
        auth = Firebase.auth(FirebaseApp.initializeApp(requireContext())!!)
        initObserver()
        binding.btnBack.setOnClickListener {
            onBackPress()
        }

        binding.btnNext.setOnClickListener {
            viewModel.verifyPhoneNumber(binding.edtPhoneNo.editText?.text.toString(), auth, requireActivity())
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

    private fun initObserver(){
        viewModel.errorPhone.observe(this, {
            it.getContentIfNotHandled()?.let {error->
                binding.edtPhoneNo.error = error
            }
        })

        viewModel.showVerificationPage.observe(this, {
            it.getContentIfNotHandled()?.let {
                binding.lytInputPhone.visibility = View.GONE
                binding.lytOTP.visibility = View.VISIBLE
            }
        })
    }
}