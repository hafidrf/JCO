package com.jcodonuts.app.ui.main.menu_search

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.CommonSearch
import com.jcodonuts.app.data.local.HomeMenuItem
import com.jcodonuts.app.data.local.MenuSearchTagName
import com.jcodonuts.app.homeMenuItem
import com.jcodonuts.app.menuSearchTagview
import com.jcodonuts.app.searchview
import com.jcodonuts.app.ui.main.home.HomeControllerListener

/**
 * Showcases [EpoxyController] with sticky header support
 */
class MenuSearchController(
    private val listener: HomeControllerListener
) : AsyncEpoxyController() {

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEachIndexed { index, cellData ->
            when(cellData) {
                is CommonSearch -> addCommonSearch()
                is MenuSearchTagName -> addMenuSearchTagName(cellData)
                is HomeMenuItem -> addHomeMenuItem(cellData, listener, index)
            }
        }
    }

    private fun addHomeMenuItem(cellData: HomeMenuItem, listener: HomeControllerListener, index:Int) {
        homeMenuItem {
            id(cellData.hashCode())
            data(cellData)
            index(index)
            onClickListener(listener)
        }
    }

    private fun addCommonSearch(){
        searchview {
            id("menuSearchTagview")
            spanSizeOverride { _, _, _ -> 2 }
        }
    }

    private fun addMenuSearchTagName(cellData: MenuSearchTagName){
        menuSearchTagview {
            id(cellData.hashCode())
            data(cellData)
            spanSizeOverride { _, _, _ -> 2 }
        }
    }

}
