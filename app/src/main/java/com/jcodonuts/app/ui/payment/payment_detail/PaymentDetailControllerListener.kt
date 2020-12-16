package com.jcodonuts.app.ui.payment.payment_detail

import com.jcodonuts.app.data.local.*

interface PaymentDetailControllerListener {
    fun onOrderNumberCopy(index: Int)
    fun onBtnOrderClick(index: Int)
    fun onBtnTrackingClick(index: Int)
}