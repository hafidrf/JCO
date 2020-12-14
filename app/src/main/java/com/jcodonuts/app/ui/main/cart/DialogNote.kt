package com.jcodonuts.app.ui.main.cart

import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.DlgCannotPlaceOrderBinding
import com.jcodonuts.app.databinding.DlgCartProductNoteBinding

class DialogNote() : BottomSheetDialogFragment() {

    private lateinit var listener : OnDialogClickListener
    private lateinit var binding : DlgCartProductNoteBinding
    override fun getTheme(): Int {
        return R.style.DialogFullWidth
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DlgCartProductNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        binding.btnDlgClose.setOnClickListener {
            dialog?.cancel()
        }
        binding.btnSave.setOnClickListener {
            listener.onSaveClick(binding.edtNote.text.toString())
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
        fun onSaveClick(notes:String)
    }

}