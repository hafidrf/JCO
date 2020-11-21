package com.jcodonuts.app.ui.payment.linkaja

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SingleEvents
import javax.inject.Inject

class LinkajaViewModel @Inject constructor(): BaseViewModel(), LinkajaControllerListener {

    private val _datas = MutableLiveData<List<BaseCell>>()
    val datas : LiveData<List<BaseCell>>
        get() = _datas

    fun loadData(){
        val temp = _datas.value?.toMutableList() ?: mutableListOf()
        temp.add(LinkajaCard("08534567865", "Rp. 100.000"))
        temp.add(LinkajaSectionHeader("Top Up"))
        temp.add(LinkajaTopupType("ATM"))
        temp.add(LinkajaTopupType("M-Banking"))
        temp.add(LinkajaTopupType("i-Banking"))
        temp.add(Divider16())
        temp.add(LinkajaSectionHeader("Last Transaction"))
        temp.add(Divider16())
        temp.add(LinkajaTransactionDate("08 October 2020"))
        temp.add(LinkajaTransaction("Jco Cihampelas Walk", "Payment", "Rp. 87.000"))
        temp.add(LinkajaTransaction("Jco Cihampelas Walk", "Payment", "Rp. 87.000"))
        temp.add(LinkajaTransaction("Jco Cihampelas Walk", "Payment", "Rp. 87.000"))
        temp.add(Divider16())
        temp.add(Divider16())
        temp.add(LinkajaTransactionDate("04 October 2020"))
        temp.add(LinkajaTransaction("Jco Cihampelas Walk", "Payment", "Rp. 87.000"))
        temp.add(LinkajaTransaction("Jco Cihampelas Walk", "Payment", "Rp. 87.000"))
        temp.add(LinkajaTransaction("Jco Cihampelas Walk", "Payment", "Rp. 87.000"))

        _datas.postValue(temp)
    }

    override fun onTransactionClick(linkajaTransaction: LinkajaTransaction) {

    }

    override fun onTypeClick(linkajaTopupType: LinkajaTopupType) {

    }

}