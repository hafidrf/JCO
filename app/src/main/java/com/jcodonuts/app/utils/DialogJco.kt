package com.jcodonuts.app.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import com.jcodonuts.app.R
import kotlinx.android.synthetic.main.dlg_success.view.*

class DialogJco(context: Context) : Dialog(context) {
    var dialog: Dialog? = null

    init{
        dialog = Dialog(context)
    }
    fun showPopup(title:String, desc:String, txtButton:String, onClickListener: View.OnClickListener){

        val dialogview = LayoutInflater.from(context)
            .inflate(R.layout.dlg_success, null, false)
        dialogview.dlgTitle.text = title
        dialogview.dlgDesc.text = desc
        dialogview.dlgButton.text = txtButton

        dialogview.dlgButton.setOnClickListener(onClickListener)
        //initializing dialog screen

        dialog?.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT )
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setContentView(dialogview)
//        dialog?.window!!.attributes.windowAnimations = R.style.Theme_MaterialComponents_BottomSheetDialog
        dialog?.show()

    }

    fun dismissPopup() = dialog?.let{dialog?.dismiss()}

}