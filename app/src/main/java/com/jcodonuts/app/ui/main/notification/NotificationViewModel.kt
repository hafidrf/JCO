package com.jcodonuts.app.ui.main.notification

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.ModelNotification
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.ui.base.BaseViewModel
import javax.inject.Inject

class NotificationViewModel @Inject constructor(): NotificationControllerListener, BaseViewModel() {

    val datas = MutableLiveData<MutableList<BaseCell>>()

    init {
        datas.value = mutableListOf()
    }

    @SuppressLint("CheckResult")
    fun loadData(){
        datas.value?.let {
            it.add(ModelNotification("https://drive.google.com/uc?id=1T5xTwCtzK5S4G9_tJ_ZYFVeQtT3k9iEN","We have sent your order","Orders with number 257425427 are being sent with Gojek.","14 Oct",""))
            it.add(ModelNotification("https://drive.google.com/uc?id=1rWbQmCCUQgXS7Q2fJMctViLEgAq39AKN","Your order has arrived","The order with order number 257425427 has arrived. please confirm your order.","14 Oct",""))
            it.add(ModelNotification("https://drive.google.com/uc?id=1rWbQmCCUQgXS7Q2fJMctViLEgAq39AKN","Processing your order","Orders with order no. 257425427 are being processed.","14 Oct",""))
            it.add(ModelNotification("https://drive.google.com/uc?id=1rWbQmCCUQgXS7Q2fJMctViLEgAq39AKN","Processing your order","Processing your order","9 oct",""))
            it.add(ModelNotification("https://drive.google.com/uc?id=1A7zphxpTvwkHz8CL4_l6DWVJ3XDG3wed","Completed your payment","Please complete the payment to the BCA Virtual Account 884246284624","9 Oct",""))
            datas.postValue(it)
        }
    }

    override fun onClick(index: Int) {

    }


}