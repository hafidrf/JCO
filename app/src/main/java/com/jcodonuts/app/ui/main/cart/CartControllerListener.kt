package com.jcodonuts.app.ui.main.cart


interface CartControllerListener {
    fun onClick(index: Int)
    fun onSwitchChange(checked: Boolean)
    fun onPickupAddressClick(index:Int)
    fun onDeliveryAddressClick(index:Int)
    fun onOrderClick(index:Int)
    fun onChangePaymentClick(index:Int)
    fun onProductClick(index:Int)
    fun onProductPlusClick(index:Int)
    fun onProductMinusClick(index:Int)
    fun onProductNotesClick(index:Int)
}