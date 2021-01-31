package com.jcodonuts.app.ui.auth.forgot_password

import android.os.Bundle
import android.util.Log
import android.view.View
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentForgotPasswordBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.DialogJco
import com.jcodonuts.app.utils.KeyboardUtil
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
        KeyboardUtil(requireActivity(), binding.root)
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

        binding.btnResetPassword.setOnClickListener {
            val dlg = DialogJco(requireContext())
            dlg.showPopup(
                getString(R.string.dlg_password_reset_title),
                getString(R.string.dlg_password_reset_desc),
                getString(R.string.create),
                View.OnClickListener{
                    dlg.dismissPopup()
                    binding.lytOTP.visibility = View.GONE
                    binding.lytCreatePassword.visibility = View.VISIBLE
            })
        }

        binding.btnSavePassword.setOnClickListener {
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
    }
}