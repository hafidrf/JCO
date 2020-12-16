package com.jcodonuts.app.ui.pickup

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.jcodonuts.app.*
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.ui.main.menu_search.MenuSearchControllerListener

/**
 * Showcases [EpoxyController] with sticky header support
 */
class PickupController(
    private val listener: PickupControllerListener
) : AsyncEpoxyController() {

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEachIndexed { index, cellData ->
            when(cellData) {
                is LocationSearch -> addLocationSearch()
                is PickupItem -> addPickupItem(cellData, listener, index)
            }
        }
    }

    private fun addLocationSearch(){
        locationSearchview {
            id("locationSearch")
        }
    }

    private fun addPickupItem(cellData: PickupItem, listener: PickupControllerListener, index:Int){
        pickupItem {
            id(cellData._id)
            data(cellData)
            index(index)
            listener(listener)
        }
    }

}
