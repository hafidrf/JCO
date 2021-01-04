package com.jcodonuts.app.ui.edit_profile

import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.DlgBirthDateBinding
import com.jcodonuts.app.databinding.DlgCannotPlaceOrderBinding
import com.jcodonuts.app.databinding.DlgChangeAppBinding
import com.jcodonuts.app.databinding.DlgGenderBinding

class DialogGender() : BottomSheetDialogFragment() {

    private lateinit var listener : OnDialogClickListener
    private lateinit var binding : DlgGenderBinding
    override fun getTheme(): Int {
        return R.style.DialogFullWidth
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DlgGenderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        binding.btnDlgClose.setOnClickListener {
            dialog?.cancel()
        }
//        binding.btnCancel.setOnClickListener {
//            dialog?.cancel()
//        }

    }

    fun showDialog(fragmentManager: FragmentManager, listener : OnDialogClickListener) {
        show(fragmentManager, "dlg_gender")
        this.listener = listener
    }

    fun dissmissDialog(){
        dialog?.cancel()
    }

    interface OnDialogClickListener{
        fun onChange()
    }

}