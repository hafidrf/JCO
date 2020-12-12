package com.jcodonuts.app.ui.main.cart

import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.CartSwitch
import com.jcodonuts.app.ui.base.BaseViewModel
import javax.inject.Inject

class CartViewModel @Inject constructor(): BaseViewModel(), CartControllerListener {

    val datas = MutableLiveData<MutableList<BaseCell>>()

    init {
        datas.value = mutableListOf()
    }

    fun loadData(){
        datas.value?.let {
            it.add(CartSwitch(false))
            it.add(CartSwitch(true))
            datas.postValue(it)
        }
    }

    override fun onClick(index: Int) {

    }
}