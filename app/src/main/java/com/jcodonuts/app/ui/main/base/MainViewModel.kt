package com.jcodonuts.app.ui.main.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SharedPreference
import com.jcodonuts.app.utils.SingleEvents
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val sharedPreference: SharedPreference
): BaseViewModel() {

    private val _showBottomNavigation = MutableLiveData<SingleEvents<String>>()
    val showBottomNavigation : LiveData<SingleEvents<String>>
        get() = _showBottomNavigation

    private val _hideBottomNavigation = MutableLiveData<SingleEvents<String>>()
    val hideBottomNavigation : LiveData<SingleEvents<String>>
        get() = _hideBottomNavigation

    fun loginCheck(){
        val data = sharedPreference.getValueString(SharedPreference.ACCESS_TOKEN)
        if(data != null && data != "") {
            _showBottomNavigation.value = SingleEvents("show buttomNavigation")
        }else{
            _hideBottomNavigation.value = SingleEvents("hide buttomNavigation")
        }
    } 
}