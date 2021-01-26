package com.jcodonuts.app.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.DlgSuccessBinding

class DialogError(private val context: Context){
    var dialog: Dialog? = null

    lateinit var binding : DlgSuccessBinding

    init{
        dialog = Dialog(context, R.style.AppTheme_AppCompat_Dialog_Alert_NoFloating)
    }

    fun showPopup(desc:String, onClickListener: View.OnClickListener){

        binding = DlgSuccessBinding.inflate(LayoutInflater.from(context))

        binding.imgTop.setImageResource(R.drawable.ic_baseline_error)
        binding.dlgTitle.text = context.getString(R.string.error)
        binding.dlgDesc.text = desc
        binding.dlgButton.text = context.getString(R.string.ok)

        binding.dlgButton.setOnClickListener(onClickListener)
        //initializing dialog screen

        dialog?.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT )
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setContentView(binding.root)
        dialog?.show()

    }

    fun dismissPopup() = dialog?.let{dialog?.dismiss()}

}