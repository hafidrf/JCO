package com.jcodonuts.app.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.DlgSuccessBinding

class DialogJco(context: Context) : Dialog(context) {
    var dialog: Dialog? = null

    lateinit var binding : DlgSuccessBinding

    init{
        dialog = Dialog(context)
    }
    fun showPopup(title:String, desc:String, txtButton:String, onClickListener: View.OnClickListener){

        binding = DlgSuccessBinding.inflate(LayoutInflater.from(context))

//        val dialogview = LayoutInflater.from(context)
//            .inflate(R.layout.dlg_success, null, false)
        binding.dlgTitle.text = title
        binding.dlgDesc.text = desc
        binding.dlgButton.text = txtButton

        binding.dlgButton.setOnClickListener(onClickListener)
        //initializing dialog screen

        dialog?.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT )
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setContentView(binding.root)
//        dialog?.window!!.attributes.windowAnimations = R.style.Theme_MaterialComponents_BottomSheetDialog
        dialog?.show()

    }

    fun dismissPopup() = dialog?.let{dialog?.dismiss()}

}