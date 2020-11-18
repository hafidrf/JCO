package com.jcodonuts.app.ui.mainHome

import android.util.Log
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.jcodonuts.app.*
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.Carousels

class HomeController() : TypedEpoxyController<List<BaseCell>>() {
    private val TAG = "HomeController"

    override fun buildModels(data: List<BaseCell>) {
        Log.d(TAG, "buildModels: ");
        homeHeader {
            id("header")
            userName("Test My Name")

        }
        homeSearch {
            id("search")
        }
        homeCarouselHeader {
            id("carousel-header")
        }

        data.forEach { cellData ->
            when(cellData) {
                is Carousels -> addCarousels(cellData)
            }
        }

        homeMenuHeader {
            id("menu_header")
        }
    }

    private fun addCarousels(cellData: Carousels) {
        val models = cellData.carousels.map {
            HomeCarouselBindingModel_()
                .id(it.imgURL)
                .imgURL(it.imgURL)
        }
        carousel {
            id("carousel")
            padding(Carousel.Padding.dp(16, 4, 16, 16, 8))
            models(models)
        }
    }
}