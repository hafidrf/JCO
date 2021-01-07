package com.jcodonuts.app.ui.edit_profile

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.DlgBirthDateBinding
import com.jcodonuts.app.databinding.DlgCannotPlaceOrderBinding
import com.jcodonuts.app.databinding.DlgChangeAppBinding

class DialogBirthDate() : BottomSheetDialogFragment() {

    private lateinit var listener : OnDialogClickListener
    private lateinit var binding : DlgBirthDateBinding
    override fun getTheme(): Int {
        return R.style.DialogFullWidth
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DlgBirthDateBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        binding.btnSave.setOnClickListener {
            dialog?.cancel()
        }
        binding.btnCancel.setOnClickListener {
            dialog?.cancel()
        }

        dialog?.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

            try {
                bottomSheetBehavior.addBottomSheetCallback(object :BottomSheetBehavior.BottomSheetCallback(){
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                       if(newState == BottomSheetBehavior.STATE_DRAGGING){
                           bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                       }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {

                    }
                })
            } catch (e:NoSuchFieldException) {
                e.printStackTrace();
            } catch (e:IllegalAccessException) {
                e.printStackTrace();
            }
        }


    }

    fun showDialog(fragmentManager: FragmentManager, listener : OnDialogClickListener) {
        show(fragmentManager, "dlg_birth_date")
        this.listener = listener
    }

    fun dissmissDialog(){
        dialog?.cancel()
    }

    interface OnDialogClickListener{
        fun onChange()
    }

}