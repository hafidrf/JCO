package com.jcodonuts.app.ui.main.wishlist

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.HomeMenuItem
import com.jcodonuts.app.homeMenuItem
import com.jcodonuts.app.ui.main.home.HomeControllerListener

/**
 * Showcases [EpoxyController] with sticky header support
 */
class WishlistController(
    private val listener: HomeControllerListener
) : AsyncEpoxyController() {

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEachIndexed { index, cellData ->
            when(cellData) {
                is HomeMenuItem -> addHomeMenuItem(cellData, listener, index)
            }
        }
    }

    private fun addHomeMenuItem(cellData: HomeMenuItem, listener: HomeControllerListener, index:Int) {
        homeMenuItem {
            id(cellData.hashCode())
            data(cellData)
            index(index)
            onClickListener(listener)
        }
    }

}
