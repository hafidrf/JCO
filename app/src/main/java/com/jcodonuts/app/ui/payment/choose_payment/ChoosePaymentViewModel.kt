package com.jcodonuts.app.ui.payment.choose_payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SingleEvents
import javax.inject.Inject

class ChoosePaymentViewModel @Inject constructor(): BaseViewModel(), ChoosePaymentControllerListener {

    private val _datas = MutableLiveData<List<BaseCell>>()
    val datas : LiveData<List<BaseCell>>
        get() = _datas

    private val _paymentClick = MutableLiveData<SingleEvents<ChoosePaymentData>>()
    val paymentClick : LiveData<SingleEvents<ChoosePaymentData>>
        get() = _paymentClick

    fun loadData(){
        val temp = _datas.value?.toMutableList() ?: mutableListOf()
        temp.add(ChoosePaymentData("https://seeklogo.com/images/L/link-aja-logo-F029ED0939-seeklogo.com.png","Link Aja", "Rp. 100.000"))
        temp.add(ChoosePaymentData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRBdVSIgv2ePknXUhvFZ7FO0vdIh1zhJJ_SQ&usqp=CAU","Point", "Rp. 100.000"))

        _datas.postValue(temp)
    }

    override fun onClick(index: Int) {

    }

}