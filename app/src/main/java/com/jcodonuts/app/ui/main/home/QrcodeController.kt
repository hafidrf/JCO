package com.jcodonuts.app.ui.main.home

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.carousel
import com.jcodonuts.app.*
import com.jcodonuts.app.data.local.*

class QrcodeController() : AsyncEpoxyController() {

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEach { cellData ->
            when(cellData) {
                is HomeDialogQrcode -> addHomeDialogQrcode(cellData)
            }
        }
    }

    private fun addHomeDialogQrcode(cellData: HomeDialogQrcode){
        val models = cellData.qrcodes.map {
            QrcodeItemBindingModel_()
                .id(it.imgURL)
                .data(it)
        }

        carousel {
            id("qrcode")
            padding(Carousel.Padding.dp(16, 0, 16, 16, 16))
            models(models)
        }
    }
}