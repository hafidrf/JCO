package com.jcodonuts.app.ui.main.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SingleEvents
import javax.inject.Inject

class CartViewModel @Inject constructor(): BaseViewModel(), CartControllerListener {

    val datas = MutableLiveData<MutableList<BaseCell>>()

    private val _showDelivery = MutableLiveData<SingleEvents<String>>()
    val showDelivery : LiveData<SingleEvents<String>>
        get() = _showDelivery

    private val _showPickup = MutableLiveData<SingleEvents<String>>()
    val showPickup : LiveData<SingleEvents<String>>
        get() = _showPickup

    private val _showDialogNote = MutableLiveData<SingleEvents<String>>()
    val showDialogNote : LiveData<SingleEvents<String>>
        get() = _showDialogNote

    private val _showChangePayment = MutableLiveData<SingleEvents<String>>()
    val showChangePayment : LiveData<SingleEvents<String>>
        get() = _showChangePayment

    private val _showPaymentDetail = MutableLiveData<SingleEvents<String>>()
    val showPaymentDetail : LiveData<SingleEvents<String>>
        get() = _showPaymentDetail

    init {
        datas.value = mutableListOf()
    }

    fun loadData(){
        datas.value?.let {
            it.add(CartSwitch(false))
            it.add(CartDeliveryAddress("Malang", "Jl, Malang no 5"))
            it.add(CartProduct("Donut besar", "https://drive.google.com/uc?id=1Y3rye8y4Vy-S_RPZqAwgK_Oa8lnAbEFr", "Rp. 20.000", "Donut", "", 2))
            it.add(CartProduct("Donut lagi", "https://drive.google.com/uc?id=1jCmpe6OahvpU5eOYfsw68k1EG_34O6wX", "Rp. 20.000", "Donut", "", 2))
            it.add(CartProduct("Donuttest", "https://drive.google.com/uc?id=1tHhnPtKY3RHHovzZ2uP-pMj76wFyrsrd", "Rp. 20.000", "Donut", "", 2))
            it.add(CartProduct("Donutaa", "https://drive.google.com/uc?id=10uUma0gwsJDkJdumh5tLsK5HHt9KXOL0", "Rp. 20.000", "Donut", "", 2))
            it.add(CartProduct("Donutop", "https://drive.google.com/uc?id=1A_CMszjKB3Eajt9FL7wG59eudb16UQ4A", "Rp. 20.000", "Donut", "", 2))
            it.add(CartDetail("LinkAja", "https://seeklogo.com/images/L/link-aja-logo-F029ED0939-seeklogo.com.png", "Rp.120.000", "Rp. 20.000", "Rp. 140.000"))
            datas.postValue(it)
        }
    }

    override fun onClick(index: Int) {

    }

    override fun onSwitchChange(checked: Boolean) {
        datas.value?.let {
            if(checked){
                it[1] = CartPickupAddress("Jco Buah Batu", "Jl. Buah Batu No 161, Cijagra, Kec. Lengkong, Bandung, Jawa Barat 40265", "22 Km")
            }else{
                it[1] = CartDeliveryAddress("The Sariwangi Village", "Jl, Malang no 5")
            }
            datas.postValue(it)
        }
    }

    override fun onPickupAddressClick(index: Int) {
        _showPickup.value = SingleEvents("pickup")
    }

    override fun onDeliveryAddressClick(index: Int) {
        _showDelivery.value = SingleEvents("delivery")
    }

    override fun onOrderClick(index: Int) {
        _showPaymentDetail.value = SingleEvents("payment_detail")
    }

    override fun onChangePaymentClick(index: Int) {
        _showChangePayment.value = SingleEvents("change_payment")
    }

    override fun onProductClick(index: Int) {
        
    }

    override fun onProductPlusClick(index: Int) {
        
    }

    override fun onProductMinusClick(index: Int) {
        
    }

    override fun onProductNotesClick(index: Int) {
        _showDialogNote.value = SingleEvents("notes")
    }
}