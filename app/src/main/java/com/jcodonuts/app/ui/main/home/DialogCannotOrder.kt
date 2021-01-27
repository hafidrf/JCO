package com.jcodonuts.app.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.DlgCannotPlaceOrderBinding

class DialogCannotOrder() : BottomSheetDialogFragment() {

    private lateinit var listener : OnDialogClickListener
    private lateinit var binding : DlgCannotPlaceOrderBinding
    override fun getTheme(): Int {
        return R.style.DialogFullWidth
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DlgCannotPlaceOrderBinding.inflate(inflater)
        return binding.root
//        return  inflater.inflate(R.layout.dlg_cannot_place_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        binding.btnDlgClose.setOnClickListener {
            dialog?.cancel()
        }
        binding.btnLogin.setOnClickListener {
            listener.onLoginClick()
        }
        binding.btnRegister.setOnClickListener {
            listener.onRegisterClick()
        }

    }

    fun showDialog(fragmentManager: FragmentManager, tag: String?, listener : OnDialogClickListener) {
        show(fragmentManager, tag)
        this.listener = listener
    }

    fun dissmissDialog(){
        dialog?.cancel()
    }

    interface OnDialogClickListener{
        fun onLoginClick()
        fun onRegisterClick()
    }

}