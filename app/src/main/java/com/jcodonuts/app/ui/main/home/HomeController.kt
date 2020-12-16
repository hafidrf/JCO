package com.jcodonuts.app.ui.main.home

import com.airbnb.epoxy.*
import com.jcodonuts.app.*
import com.jcodonuts.app.data.local.*

class HomeController(private val listener: HomeControllerListener)
    : AsyncEpoxyController() {
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
        data.forEachIndexed { index, cellData ->
            when(cellData) {
                is HomeHeadSection -> addHomeHeadSection(cellData, listener)
                is HomeSearchSection -> addHomeSearchSection(listener)
                is HomePromoHeader -> addHomePromoHeader()
                is HomePromos -> addHomePromos(cellData)
                is HomeMenuHeader -> addHomeMenuHeader()
                is HomeMenuCategories -> addHomeMenuCategory(cellData, listener)
                is HomeMenuItem -> addHomeMenuItem(cellData, listener, index)
            }
        }
    }

    private fun addHomeHeadSection(cellData: HomeHeadSection, listener: HomeControllerListener){
        homeHeader {
            id("header")
            spanSizeOverride { _, _, _ -> 2 }
            onClickListener(listener)
            data(cellData)
        }
    }

    private fun addHomeSearchSection(listener: HomeControllerListener){
        homeSearch {
            id("search")
            spanSizeOverride { _, _, _ -> 2 }
            listener(listener)
        }
    }

    private fun addHomePromoHeader(){
        homePromoHeader {
            id("carousel-header")
            spanSizeOverride { _, _, _ -> 2 }
        }
    }

    private fun addHomeMenuHeader(){
        homeMenuHeader {
            id("menu_header")
            spanSizeOverride { _, _, _ -> 2 }
        }
    }

    private fun addHomePromos(cellData: HomePromos) {
        val models = cellData.promos.map {
            HomePromoBindingModel_()
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

    private fun addHomeMenuCategory(cellData: HomeMenuCategories, listener: HomeControllerListener) {
        val models = cellData.homeMenus.map {
            HomeMenuBindingModel_()
                    .id(it.img.toString())
                    .data(it)
                    .selected(it.img == menuSelected)
                    .onClickListener(listener)
        }

        carousel {
            id("menuHeader")
            padding(Carousel.Padding.dp(16, 4, 16, 16, 8))
            models(models)
            spanSizeOverride { _, _, _ -> 2 }
        }
    }

    private fun addHomeMenuItem(cellData: HomeMenuItem, listener: HomeControllerListener, index:Int) {
            homeMenuItem {
                id(cellData.imgURL)
                data(cellData)
                index(index)
                onClickListener(listener)
            }
    }
}