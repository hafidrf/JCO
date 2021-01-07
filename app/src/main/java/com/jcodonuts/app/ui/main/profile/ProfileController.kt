package com.jcodonuts.app.ui.main.profile

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.jcodonuts.app.*
import com.jcodonuts.app.data.local.*

/**
 * Showcases [EpoxyController] with sticky header support
 */
class ProfileController(
    private val listener: ProfileControllerListener
) : TypedEpoxyController<List<BaseCell>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    override fun buildModels(data: List<BaseCell>?) {
        data?.forEachIndexed() { index, cellData ->
            when(cellData) {
                is ProfileHeader -> addProfileHeader(cellData, listener)
                is ProfileMenuHeader -> addProfileMenuHeader(cellData)
                is ProfileMenu -> addProfileMenu(cellData, index, listener)
                is ProfileFooter -> addProfileFooter()
            }
        }
    }

    private fun addProfileHeader(cellData: ProfileHeader, listener:ProfileControllerListener){
        profileHeader {
            id("switch_cart")
            data(cellData)
            listener(listener)
        }
    }

    private fun addProfileMenuHeader(cellData:ProfileMenuHeader){
        profileMenuHeader {
            id("delivery_address")
            data(cellData)
        }
    }

    private fun addProfileMenu(cellData:ProfileMenu, index:Int, listener:ProfileControllerListener){
        profileMenu {
            id("pickup_address")
            data(cellData)
            index(index)
            listener(listener)
        }
    }

    private fun addProfileFooter(){
        profileFooter {
            id("profile_footer")
        }
    }

}