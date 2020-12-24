package com.jcodonuts.app.ui.hot_promo

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.data.repository.PaymentRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SchedulerProvider
import com.jcodonuts.app.utils.SingleEvents
import io.reactivex.Scheduler
import javax.inject.Inject

class HotPromoViewModel @Inject constructor(): HotPromoControllerListener, BaseViewModel() {

    private val _datas = MutableLiveData<List<BaseCell>>()
    val datas : LiveData<List<BaseCell>>
        get() = _datas

    @SuppressLint("CheckResult")
    fun loadData(){
        val temp = _datas.value?.toMutableList() ?: mutableListOf()
        temp.add(ModelHotPromo("https://drive.google.com/uc?id=1Y9bhAdVFmfGo2LWxzzNZAF86EkPIzXyR","1 dzn Donuts + 2 dzn Jpops","9 Nov 2020 - 13 Nov 2020"))
        temp.add(ModelHotPromo("https://drive.google.com/uc?id=1Tr7t-GmOuZtsO7138m6vUNOSSZylRt0G","Family Package","9 Nov 2020 - 13 Nov 2020"))
        temp.add(ModelHotPromo("https://drive.google.com/uc?id=11BtCHTCFE_lyXEqNjrdBLxMkN2xApeVl","Buy More, Save More.","1 Nov 2020 - 15 Nov 2020"))
        _datas.postValue(temp)
    }

    override fun onClick(index: Int) {

    }
}