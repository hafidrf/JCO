package com.jcodonuts.app.ui.delivery

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.LocationSearch
import com.jcodonuts.app.data.repository.DeliveryRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SchedulerProvider
import javax.inject.Inject

class DeliveryViewModel @Inject constructor(
        private val deliveryRepository: DeliveryRepository,
        private val schedulers: SchedulerProvider
): BaseViewModel(), DeliveryControllerListener {
    private val TAG = "DeliveryViewModel"

    val datas = MutableLiveData<MutableList<BaseCell>>()

    init {
        datas.value = mutableListOf()
    }

    @SuppressLint("CheckResult")
    fun loadLocations(){
        deliveryRepository.getListLocations()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({ data ->
                    datas.value?.let {
                        it.add(LocationSearch("temp"))
                        it.addAll(data)
                        datas.postValue(it)
                    }
                }, {

                })
    }

    fun SearchLocations(query:String){
        Log.d(TAG, query)
    }

    override fun onClick(index: Int) {

    }

    override fun onFavoriteClick(index: Int) {

    }
}