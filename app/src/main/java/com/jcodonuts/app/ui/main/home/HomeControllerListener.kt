package com.jcodonuts.app.ui.main.home

import com.jcodonuts.app.data.local.HomeMenuItem
import com.jcodonuts.app.data.local.Menu
import com.jcodonuts.app.data.local.PromoBanner

interface HomeControllerListener {
    fun onSearchClick()
    fun onBannerPromoClick(promoBanner: PromoBanner)
    fun onPromoSeeAllClick()
    fun onMenuClick(menu:Menu)
    fun onMenuItemClick(menuItem:HomeMenuItem)
}