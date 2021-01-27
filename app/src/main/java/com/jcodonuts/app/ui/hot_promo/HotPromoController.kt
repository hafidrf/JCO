package com.jcodonuts.app.ui.hot_promo

import com.airbnb.epoxy.AsyncEpoxyController
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.ModelHotPromo
import com.jcodonuts.app.hotPromo

class HotPromoController(val listener: HotPromoControllerListener) : AsyncEpoxyController() {
    private val TAG = "TopupController"

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEachIndexed { index, cellData ->
            when(cellData) {
                is ModelHotPromo -> addModelHotPromo(cellData, listener, index)
            }
        }
    }

    private fun addModelHotPromo(cellData: ModelHotPromo, listener: HotPromoControllerListener, index:Int){
        hotPromo {
            id(cellData.img)
            data(cellData)
            index(index)
            listener(listener)
        }
    }
}