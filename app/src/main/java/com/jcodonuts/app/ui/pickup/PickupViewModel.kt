package com.jcodonuts.app.ui.pickup

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.LocationSearch
import com.jcodonuts.app.data.repository.PickupRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SchedulerProvider
import javax.inject.Inject

class PickupViewModel @Inject constructor(
        private val homeRepository: PickupRepository,
        private val schedulers: SchedulerProvider
): BaseViewModel(), PickupControllerListener {
    private val TAG = "PickupViewModel"

    val datas = MutableLiveData<MutableList<BaseCell>>()

    init {
        datas.value = mutableListOf()
    }

    @SuppressLint("CheckResult")
    fun loadLocations(){
        homeRepository.getListLocations()
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