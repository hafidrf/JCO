package com.jcodonuts.app.ui.delivery

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.jcodonuts.app.*
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.ui.main.menu_search.MenuSearchControllerListener

/**
 * Showcases [EpoxyController] with sticky header support
 */
class DeliveryController(
    private val listener: DeliveryControllerListener
) : AsyncEpoxyController() {

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data?.forEachIndexed { index, cellData ->
            when(cellData) {
                is LocationSearch -> addLocationSearch()
                is DeliveryItem -> addDeliveryItem(cellData, listener, index)
            }
        }
    }

    private fun addLocationSearch(){
        locationSearchview {
            id("locationSearch")
        }
    }

    private fun addDeliveryItem(cellData: DeliveryItem, listener: DeliveryControllerListener, index:Int){
        deliveryItem {
            id(cellData._id)
            data(cellData)
            index(index)
            listener(listener)
        }
    }

}
