package com.jcodonuts.app.ui.payment.payment_detail

import com.airbnb.epoxy.AsyncEpoxyController
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.OrderDetail
import com.jcodonuts.app.data.local.OrderProduct
import com.jcodonuts.app.data.local.OrderTotal
import com.jcodonuts.app.paymentDetailOrder
import com.jcodonuts.app.paymentDetailProduct
import com.jcodonuts.app.paymentDetailTotal

class PaymentDetailController(private val listener: PaymentDetailControllerListener) : AsyncEpoxyController() {
    private val TAG = "HomeController"

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEachIndexed { index, cellData ->
            when(cellData) {
                is OrderDetail -> addOrderDetail(cellData, listener, index)
                is OrderProduct -> addOrderProduct(cellData, index)
                is OrderTotal -> addOrderTotal(cellData, listener, index)
            }
        }
    }

    private fun addOrderDetail(cellData: OrderDetail, listener: PaymentDetailControllerListener, index:Int){
        paymentDetailOrder {
            id(cellData.hashCode())
            data(cellData)
            listener(listener)
            index(index)
        }
    }

    private fun addOrderProduct(cellData: OrderProduct, index:Int){
        paymentDetailProduct {
            id(cellData.hashCode())
            data(cellData)
            index(index)
        }
    }

    private fun addOrderTotal(cellData: OrderTotal, listener: PaymentDetailControllerListener, index:Int){
        paymentDetailTotal {
            id(cellData.hashCode())
            data(cellData)
            listener(listener)
            index(index)
        }
    }
}