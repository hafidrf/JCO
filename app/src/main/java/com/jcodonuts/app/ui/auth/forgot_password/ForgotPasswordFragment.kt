package com.jcodonuts.app.ui.auth.forgot_password

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
import com.jcodonuts.app.databinding.FragmentForgotPasswordBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.DialogError
import com.jcodonuts.app.utils.DialogJco
import com.jcodonuts.app.utils.KeyboardUtil
import com.jcodonuts.app.utils.sms.MySMSBroadcastReceiver
import com.jcodonuts.app.utils.sms.OtpListener
import javax.inject.Inject

class ForgotPasswordFragment @Inject constructor() : BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>(),
    OtpListener {

    private val TAG = "ForgotPasswordFragment"
    private lateinit var auth: FirebaseAuth

    override fun getViewModelClass(): Class<ForgotPasswordViewModel> {
        return ForgotPasswordViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_forgot_password

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

        binding.btnSend.setOnClickListener {
            requestOTP()
//            binding.lytInputPhone.visibility = View.GONE
//            binding.lytOTP.visibility = View.VISIBLE
        }

        binding.btnVerification.setOnClickListener {
            viewModel.verifyPhoneNumberWithCode(binding.otpView.text.toString())
        }

        binding.otpView.setOtpCompletionListener {
            Log.d(TAG, it);
        }

//        binding.btnResetPassword.setOnClickListener {
//            val dlg = DialogJco(requireContext())
//            dlg.showPopup(
//                getString(R.string.dlg_password_reset_title),
//                getString(R.string.dlg_password_reset_desc),
//                getString(R.string.create),
//                View.OnClickListener{
//                    dlg.dismissPopup()
//                    binding.lytOTP.visibility = View.GONE
//                    binding.lytCreatePassword.visibility = View.VISIBLE
//            })
//        }

        binding.btnSavePassword.setOnClickListener {
            binding.edtPassword.error = null
            binding.edtVerifyPassword.error = null

            val phone = "0"+ binding.edtPhoneNo.editText?.text.toString()
            viewModel.changePassword(phone,
                binding.edtPassword.editText?.text.toString(),
                binding.edtVerifyPassword.editText?.text.toString())
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

        viewModel.showResetFormPage.observe(this, {
            it.getContentIfNotHandled()?.let {
                binding.lytOTP.visibility = View.GONE
                binding.lytCreatePassword.visibility = View.VISIBLE
            }
        })

        viewModel.showToast.observe(this, {
            it.getContentIfNotHandled()?.let {message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.closeResetPage.observe(this, {
            it.getContentIfNotHandled()?.let {
                val dlg = DialogJco(requireContext())
                dlg.showPopup(
                    getString(R.string.dlg_password_save_title),
                    getString(R.string.dlg_password_save_desc),
                    getString(R.string.login),
                    View.OnClickListener{
                        dlg.dismissPopup()
                        onBackPress()
                    })
            }
        })

        viewModel.resetFailed.observe(this, {
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