package com.jcodonuts.app.ui.auth.register

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentRegisterBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.DialogError
import com.jcodonuts.app.utils.DialogJco
import com.jcodonuts.app.utils.KeyboardUtil
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
            requestOTP()
        }

        binding.btnVerification.setOnClickListener {
            viewModel.verifyPhoneNumberWithCode(binding.otpView.text.toString())
        }

        binding.btnCreate.setOnClickListener {
            binding.edtFullName.error = null
            binding.edtEmail.error = null
            binding.edtPassword.error = null
            binding.edtVerifyPassword.error = null

            viewModel.register(binding.edtFullName.editText?.text.toString(),
            binding.edtEmail.editText?.text.toString(),
            binding.edtPhoneNoReg.editText?.text.toString(),
            binding.edtPassword.editText?.text.toString(),
            binding.edtVerifyPassword.editText?.text.toString())
//            val dialog = DialogJco(requireContext())
//            dialog.showPopup(getString(R.string.your_account_has_been_created),
//            getString(R.string.please_login_to_place_an_order), getString(R.string.login)
//            ) {
//                dialog.dismissPopup();
//                onBackPress()
//            }
        }
    }

    private fun requestOTP(){
        viewModel.requestOTP(binding.ccp.fullNumberWithPlus, auth, requireActivity())
    }

    private fun initObserver(){
        binding.viewModel = viewModel
        binding.executePendingBindings()

        viewModel.errorPhone.observe(this, {
            it.getContentIfNotHandled()?.let {error->
                binding.edtPhoneNo.error = error
            }
        })

        viewModel.errorFullName.observe(this, {
            it.getContentIfNotHandled()?.let {error->
                binding.edtFullName.error = error
            }
        })

        viewModel.errorEmail.observe(this, {
            it.getContentIfNotHandled()?.let {error->
                binding.edtEmail.error = error
            }
        })

        viewModel.errorPassword.observe(this, {
            it.getContentIfNotHandled()?.let {error->
                binding.edtPassword.error = error
            }
        })

        viewModel.errorConfirmPassword.observe(this, {
            it.getContentIfNotHandled()?.let {error->
                binding.edtVerifyPassword.error = error
            }
        })

        viewModel.doResendOTP.observe(this, {
            it.getContentIfNotHandled()?.let {
                requestOTP()
            }
        })

        viewModel.showVerificationPage.observe(this, {
            it.getContentIfNotHandled()?.let {
                binding.lytInputPhone.visibility = View.GONE
                binding.lytOTP.visibility = View.VISIBLE
            }
        })

        viewModel.showRegisterFormPage.observe(this, {
            it.getContentIfNotHandled()?.let {
                binding.lytOTP.visibility = View.GONE
                binding.lytRegister.visibility = View.VISIBLE

                val phone = "0"+ binding.edtPhoneNo.editText?.text.toString()
                binding.edtPhoneNoReg.editText?.setText(phone)

            }
        })

        viewModel.showToast.observe(this, {
            it.getContentIfNotHandled()?.let {message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.closeRegisterPage.observe(this, {
            it.getContentIfNotHandled()?.let {
                onBackPress()
            }
        })

        viewModel.registerFailed.observe(this, {
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
        viewModel.verifyPhoneNumberWithCode(otp)
    }
}