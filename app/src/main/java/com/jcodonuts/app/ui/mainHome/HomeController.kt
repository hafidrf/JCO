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
                is HomeMenuItems -> addHomeMenuItem(cellData, listener)
            }
        }
    }

    private fun addHomeHeadSection(cellData: HomeHeadSection){
        homeHeader {
            id("header")
            userName("Test My Name")
            spanSizeOverride { _, _, _ -> 2 }
        }
    }

    private fun addHomeSearchSection(cellData: HomeSearchSection){
        homeSearch {
            id("search")
            spanSizeOverride { _, _, _ -> 2 }
        }
    }

    private fun addHomePromoHeader(cellData: HomePromoHeader){
        homeCarouselHeader {
            id("carousel-header")
            spanSizeOverride { _, _, _ -> 2 }
        }
    }

    private fun addHomeMenuHeader(cellData: HomeMenuHeader){
        homeMenuHeader {
            id("menu_header")
            spanSizeOverride { _, _, _ -> 2 }
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
            spanSizeOverride { _, _, _ -> 2 }
        }
    }

    private fun addHomeMenu(cellData: HomeMenus, listener: HomeControllerListener) {
        val models = cellData.homeMenus.map {
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
            spanSizeOverride { _, _, _ -> 2 }
        }
    }

    private fun addHomeMenuItem(cellData: HomeMenuItems, listener: HomeControllerListener) {
        cellData.homeMenuItems.map{
            homeMenuItem {
                id(it.hashCode().toString())
                data(it)
            }
        }
    }
}