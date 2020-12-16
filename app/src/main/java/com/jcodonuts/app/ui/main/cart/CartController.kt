package com.jcodonuts.app.ui.main.cart

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.jcodonuts.app.*
import com.jcodonuts.app.data.local.*

/**
 * Showcases [EpoxyController] with sticky header support
 */
class CartController(
    private val listener: CartControllerListener
) : TypedEpoxyController<List<BaseCell>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    override fun buildModels(data: List<BaseCell>?) {
        data?.forEachIndexed() { index, cellData ->
            when(cellData) {
                is CartSwitch -> addCartSwitch(listener)
                is CartDeliveryAddress -> addCartDeliveryAddress(cellData, index, listener)
                is CartPickupAddress -> addCartPickupAddress(cellData, index, listener)
                is CartProduct -> addCartProduct(cellData, index, listener)
                is CartDetail -> addCartDetail(cellData, index, listener)
            }
        }
    }

    private fun addCartSwitch(listener:CartControllerListener){
        cartSwitch {
            id("switch_cart")
            listener(listener)
        }
    }

    private fun addCartDeliveryAddress(cellData:CartDeliveryAddress, index:Int, listener:CartControllerListener){
        cartDeliveryAddress {
            id("delivery_address")
            data(cellData)
            index(index)
            listener(listener)
        }
    }

    private fun addCartPickupAddress(cellData:CartPickupAddress, index:Int, listener:CartControllerListener){
        cartPickupAddress {
            id("pickup_address")
            data(cellData)
            index(index)
            listener(listener)
        }
    }

    private fun addCartProduct(cellData:CartProduct, index:Int, listener:CartControllerListener){
        cartProduct {
            id(cellData.imgURL)
            data(cellData)
            index(index)
            listener(listener)
        }
    }

    private fun addCartDetail(cellData:CartDetail, index:Int, listener:CartControllerListener){
        cartDetail {
            id("cart_detail")
            data(cellData)
            index(index)
            listener(listener)
        }
    }

}
