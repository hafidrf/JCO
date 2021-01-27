package com.jcodonuts.app.ui.auth.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.HintRequest
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentRegisterBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.DialogJco
import com.jcodonuts.app.utils.KeyboardUtil
import com.jcodonuts.app.utils.sms.AppSignatureHelper
import com.jcodonuts.app.utils.sms.MySMSBroadcastReceiver
import com.jcodonuts.app.utils.sms.OtpListener
import javax.inject.Inject

class RegisterFragment @Inject constructor() : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(), OtpListener {
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
        initSMSReceiver()
        binding.ccp.registerCarrierNumberEditText(binding.edtPhoneNo.editText)
        binding.btnBack.setOnClickListener {
            onBackPress()
        }

        binding.btnNext.setOnClickListener {
            Log.d("DATA__", binding.ccp.fullNumberWithPlus)
            viewModel.verifyPhoneNumber(binding.ccp.fullNumberWithPlus, auth, requireActivity())
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

    private fun initSMSReceiver() {
        val client = SmsRetriever.getClient(requireActivity())
        val task = client.startSmsRetriever()
        task.addOnSuccessListener {
            Log.i(TAG, "SMS RETRIEVER TASK SUCCESSFULLY ADDED")
        }
        task.addOnFailureListener {
            Log.i(TAG, "SMS RETRIEVER TASK FAILED")
        }
        MySMSBroadcastReceiver.otpListener = this
    }

    override fun onOtpReceived(otp: String) {
        binding.otpView.setText(otp)
    }
}