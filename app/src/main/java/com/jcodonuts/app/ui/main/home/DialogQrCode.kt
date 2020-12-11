package com.jcodonuts.app.ui.main.home

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.HomeDialogQrcode
import com.jcodonuts.app.data.local.Qrcode
import com.jcodonuts.app.databinding.DlgCannotPlaceOrderBinding
import com.jcodonuts.app.databinding.DlgHomeQrcodeBinding


class DialogQrCode() : BottomSheetDialogFragment() {

    private lateinit var binding : DlgHomeQrcodeBinding
    override fun getTheme(): Int {
        return R.style.DialogFullWidth
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DlgHomeQrcodeBinding.inflate(inflater)
        return binding.root
//        return  inflater.inflate(R.layout.dlg_home_qrcode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        binding.btnDlgClose.setOnClickListener {
            dissmissDialog()
        }

        val controller = QrcodeController()
        binding.qrcodeRecyclerview.setController(controller)

        val listQr = mutableListOf<Qrcode>()
        listQr.add(
            Qrcode(
                "Rp. 105,000",
                "https://www.brandchannel.com/wp-content/uploads/2011/05/sunmaid_kungfu_qr_200.jpg",
                "09876789222"
            )
        )
        listQr.add(
            Qrcode(
                "Rp. 115,000",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Example_barcode.svg/1200px-Example_barcode.svg.png",
                "06575376475"
            )
        )

        controller.data = listOf(HomeDialogQrcode(listQr))
        dialog?.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    fun showDialog(fragmentManager: FragmentManager) {
        show(fragmentManager, "DialogQrcode")
    }

    fun dissmissDialog(){
        dialog?.setOnShowListener(null)
        dialog?.cancel()
    }
}