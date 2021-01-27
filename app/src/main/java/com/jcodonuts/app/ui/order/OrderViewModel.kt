package com.jcodonuts.app.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.ModelOrder
import com.jcodonuts.app.data.local.ModelOrderHeader
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SingleEvents
import javax.inject.Inject

class OrderViewModel @Inject constructor(): BaseViewModel(), OrderControllerListener {

    private val _datas = MutableLiveData<List<BaseCell>>()
    val datas : LiveData<List<BaseCell>>
        get() = _datas

    private val _orderClick = MutableLiveData<SingleEvents<ModelOrder>>()
    val orderClick : LiveData<SingleEvents<ModelOrder>>
        get() = _orderClick

    fun loadData(){
        val temp = _datas.value?.toMutableList() ?: mutableListOf()
        temp.add(ModelOrderHeader("08 Oct 2020"))
        temp.add(ModelOrder("Jco Cihampelas Walk","Orders with number 257425427 | 8 Item (Donuts, Jcool, Coffee)","15.00","Rp. 87.000","Completed","#50D890"))
        temp.add(ModelOrder("Jco Cihampelas Walk","Orders with number 257425427 | 8 Item (Donuts, Jcool, Coffee)","15.00","Rp. 87.000","Cancelled","#EC0101"))
        temp.add(ModelOrderHeader("12 Oct 2020"))
        temp.add(ModelOrder("Jco Cihampelas Walk","Orders with number 257425427 | 8 Item (Donuts, Jcool, Coffee)","15.00","Rp. 87.000","Completed","#50D890"))
        temp.add(ModelOrder("Jco Cihampelas Walk","Orders with number 257425427 | 8 Item (Donuts, Jcool, Coffee)","15.00","Rp. 87.000","Cancelled","#EC0101"))

        _datas.postValue(temp)
    }

    override fun onClick(index: Int) {

    }

}