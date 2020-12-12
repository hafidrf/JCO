package com.jcodonuts.app.ui.main.home

import com.jcodonuts.app.data.local.HomeMenuCategories
import com.jcodonuts.app.data.local.MenuCategory
import com.jcodonuts.app.data.local.PromoBanner

interface HomeControllerListener {
    fun onLinkajaClick()
    fun onQrCodeClick()
    fun onPickupClick()
    fun onSearchClick()
    fun onBannerPromoClick(promoBanner: PromoBanner)
    fun onPromoSeeAllClick()
    fun onMenuCategoryClick(menuCategory: MenuCategory)
    fun onMenuItemClick(index:Int)
}