package com.jcodonuts.app.ui.mainHome

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.Carousels
import com.jcodonuts.app.data.local.Country
import com.jcodonuts.app.data.repository.HomeRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SchedulerProvider
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val schedulers: SchedulerProvider
): BaseViewModel() {
    private val TAG = "HomeViewModel"

    private val _promos = MutableLiveData<List<BaseCell>>()
    val promos: LiveData<List<BaseCell>> get() = _promos

    @SuppressLint("CheckResult")
    fun loadPromo(){
        homeRepository.getPromo()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({ data ->
                val temp = _promos.value?.toMutableList() ?: mutableListOf()
                temp.add(data)
                _promos.postValue(temp)
            }, {

            })
    }
}