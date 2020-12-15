package com.jcodonuts.app.ui.payment.choose_payment

import com.airbnb.epoxy.AsyncEpoxyController
import com.jcodonuts.app.*
import com.jcodonuts.app.data.local.*

class ChoosePaymentController(private val listener: ChoosePaymentControllerListener) : AsyncEpoxyController() {
    private val TAG = "HomeController"

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEachIndexed { index, cellData ->
            when(cellData) {
                is ChoosePaymentData -> addChoosePaymentData(cellData, listener, index)
            }
        }
    }

    private fun addChoosePaymentData(cellData: ChoosePaymentData, listener: ChoosePaymentControllerListener, index:Int){
        choosePayment {
            id(cellData.hashCode())
            data(cellData)
            listener(listener)
            index(index)
        }
    }
}