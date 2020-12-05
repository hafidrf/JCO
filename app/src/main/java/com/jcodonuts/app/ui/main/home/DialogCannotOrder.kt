package com.jcodonuts.app.ui.main.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jcodonuts.app.R
import kotlinx.android.synthetic.main.dlg_cannot_place_order.view.*

class DialogCannotOrder() : BottomSheetDialogFragment() {

    private lateinit var listener : OnDialogClickListener
    override fun getTheme(): Int {
        return R.style.DialogFullWidth
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.dlg_cannot_place_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        view.btnDlgClose.setOnClickListener {
            dialog?.cancel()
        }
        view.btnLogin.setOnClickListener {
            listener.onLoginClick()
        }
        view.btnRegister.setOnClickListener {
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