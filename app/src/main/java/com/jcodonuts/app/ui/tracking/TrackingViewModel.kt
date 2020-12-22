package com.jcodonuts.app.ui.tracking

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.data.repository.PaymentRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SchedulerProvider
import io.reactivex.Scheduler
import javax.inject.Inject

class TrackingViewModel @Inject constructor(): BaseViewModel() {

    private val _datas = MutableLiveData<List<BaseCell>>()
    val datas : LiveData<List<BaseCell>>
        get() = _datas

    @SuppressLint("CheckResult")
    fun loadData(){
        val temp = _datas.value?.toMutableList() ?: mutableListOf()
        temp.add(TrackingHeader("7654567", "Jco Cihampelas Walk"))
        temp.add(TrackingStatus("14 Oct 2020"))
        temp.add(TrackingProgress(R.drawable.img_temp_tracking_1, "We have received your order", "Jco Cihampelas Walk", "09.00", true, "start"))
        temp.add(TrackingProgress(R.drawable.img_temp_tracking_2, "Your order is being processed", "Now we are processing your order", "", false, "middle"))
        temp.add(TrackingProgress(R.drawable.img_temp_tracking_3, "Waiting a courier", "The order will be picked up by a courier from Grab", "", false, "middle"))
        temp.add(TrackingProgress(R.drawable.img_temp_tracking_4, "Your order has been sent", "Your order is being delivered by a Grab courier", "", false, "middle"))
        temp.add(TrackingProgress(R.drawable.img_temp_tracking_5, "Your order has arrived", "Your order has been received by customer", "", false, "end"))
        _datas.postValue(temp)
    }
}