package com.jcodonuts.app.ui.main.cart

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.jcodonuts.app.cartSwitch
import com.jcodonuts.app.data.local.*

/**
 * Showcases [EpoxyController] with sticky header support
 */
class CartController(
    private val listeners: CartControllerListener
) : TypedEpoxyController<List<BaseCell>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    override fun buildModels(data: List<BaseCell>?) {
        data?.forEachIndexed() { index, cellData ->
            when(cellData) {
                is CartSwitch -> addCartSwitch(cellData)
            }
        }
    }

    private fun addCartSwitch(cell: BaseCell){
        cartSwitch {
            id("switch_cart")
        }
    }

}
