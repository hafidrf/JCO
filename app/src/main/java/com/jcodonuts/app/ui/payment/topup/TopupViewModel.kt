package com.jcodonuts.app.ui.payment.topup

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.data.repository.PaymentRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SchedulerProvider
import io.reactivex.Scheduler
import javax.inject.Inject

class TopupViewModel @Inject constructor(
        private val paymentRepository: PaymentRepository,
        private val scheduler: SchedulerProvider
): BaseViewModel() {

    private val _datas = MutableLiveData<List<BaseCell>>()
    val datas : LiveData<List<BaseCell>>
        get() = _datas

    @SuppressLint("CheckResult")
    fun loadData(){
        paymentRepository.getTopupMethod()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe ( {
                    data ->
                    val temp = _datas.value?.toMutableList() ?: mutableListOf()
                    temp.add(TopupSectionHeader("Top Up via M-Banking"))
                    temp.addAll(data)
                    _datas.postValue(temp)
                },{

                }
                )
    }
}