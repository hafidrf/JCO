package com.jcodonuts.app.ui.mainHome

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.jcodonuts.app.*
import com.jcodonuts.app.data.local.*

class HomeController(private val listener: HomeControllerListener) : AsyncEpoxyController() {
    private val TAG = "HomeController"

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    var dataHomeMenu: List<Menu> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    var menuSelected = R.drawable.img_jco_menu_all
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEach { cellData ->
            when(cellData) {
                is HomeHeadSection -> addHomeHeadSection(cellData)
                is HomeSearchSection -> addHomeSearchSection(cellData)
                is HomePromoHeader -> addHomePromoHeader(cellData)
                is HomePromos -> addHomePromos(cellData)
                is HomeMenuHeader -> addHomeMenuHeader(cellData)
                is HomeMenus -> addHomeMenu(cellData, listener)
            }
        }
    }

    private fun addHomeHeadSection(cellData: HomeHeadSection){
        homeHeader {
            id("header")
            userName("Test My Name")

        }
    }

    private fun addHomeSearchSection(cellData: HomeSearchSection){
        homeSearch {
            id("search")
        }
    }

    private fun addHomePromoHeader(cellData: HomePromoHeader){
        homeCarouselHeader {
            id("carousel-header")
        }
    }

    private fun addHomeMenuHeader(cellData: HomeMenuHeader){
        homeMenuHeader {
            id("menu_header")
        }
    }

    private fun addHomePromos(cellData: HomePromos) {
        val models = cellData.promos.map {
            HomeCarouselBindingModel_()
                .id(it.imgURL)
                .imgURL(it.imgURL)
        }
        carousel {
            id("promo")
            padding(Carousel.Padding.dp(16, 4, 16, 16, 8))
            models(models)
        }
    }

    private fun addHomeMenu(cellData: HomeMenus, listener: HomeControllerListener) {
        val models = cellData.carousels.map {
            HomeMenuBindingModel_()
                    .id(it.img.toString())
                    .data(it)
                    .isSelected(it.img == menuSelected)
                    .onClickListener(listener)
        }

        carousel {
            id("menuHeader")
            padding(Carousel.Padding.dp(16, 4, 16, 16, 8))
            models(models)
        }
    }
}