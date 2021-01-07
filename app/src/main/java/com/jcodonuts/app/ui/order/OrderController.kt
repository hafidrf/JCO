package com.jcodonuts.app.ui.order

import com.airbnb.epoxy.AsyncEpoxyController
import com.jcodonuts.app.*
import com.jcodonuts.app.data.local.*

class OrderController(private val listener: OrderControllerListener) : AsyncEpoxyController() {
    private val TAG = "HomeController"

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEachIndexed { index, cellData ->
            when(cellData) {
                is ModelOrderHeader -> addModelOrderHeader(cellData)
                is ModelOrder -> addModelOrder(cellData, listener, index)
            }
        }
    }

    private fun addModelOrderHeader(cellData: ModelOrderHeader){
        orderHeader {
            id(cellData.hashCode())
            data(cellData)
        }
    }

    private fun addModelOrder(cellData: ModelOrder, listener: OrderControllerListener, index:Int){
        orderItem {
            id(cellData.hashCode())
            data(cellData)
            listener(listener)
            index(index)
        }
    }
}