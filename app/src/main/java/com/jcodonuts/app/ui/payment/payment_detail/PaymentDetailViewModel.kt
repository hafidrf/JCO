package com.jcodonuts.app.ui.payment.payment_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SingleEvents
import javax.inject.Inject

class PaymentDetailViewModel @Inject constructor(): BaseViewModel(), PaymentDetailControllerListener {

    private val _datas = MutableLiveData<List<BaseCell>>()
    val datas : LiveData<List<BaseCell>>
        get() = _datas

    private val _trackingClick = MutableLiveData<SingleEvents<String>>()
    val trackingClick : LiveData<SingleEvents<String>>
        get() = _trackingClick

    private val _orderAgainClick = MutableLiveData<SingleEvents<String>>()
    val orderAgainClick : LiveData<SingleEvents<String>>
        get() = _orderAgainClick

    fun loadData(){
        val temp = _datas.value?.toMutableList() ?: mutableListOf()
        temp.add(OrderDetail("Pickup at","Jco Cihampelas Walk","Cipaganti, Coblong, Bandung, Jawa Barat 40131","2 km", "875678", "14 October 2020"))
        temp.add(OrderProduct("Donuts 2 dzn","1","Banyakin creamnya"))
        temp.add(OrderProduct("Iced Brown Sugar 1L","1",""))
        temp.add(OrderProduct("Golden Nastar","2",""))
        temp.add(OrderProduct("Mug Groovy","1",""))
        temp.add(OrderTotal("Rp. 393.000","Rp. 22.000","0","Rp. 415.000"))

        _datas.postValue(temp)
    }

    override fun onOrderNumberCopy(index: Int) {
        
    }

    override fun onBtnOrderClick(index: Int) {
        _orderAgainClick.value = SingleEvents("orderAgainClick");
    }

    override fun onBtnTrackingClick(index: Int) {
        _trackingClick.value = SingleEvents("tracking");
    }

}