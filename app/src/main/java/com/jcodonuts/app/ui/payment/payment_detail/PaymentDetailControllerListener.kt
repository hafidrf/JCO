package com.jcodonuts.app.ui.payment.payment_detail

interface PaymentDetailControllerListener {
    fun onOrderNumberCopy(index: Int)
    fun onBtnOrderClick(index: Int)
    fun onBtnTrackingClick(index: Int)
}