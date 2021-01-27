package com.jcodonuts.app.ui.contact_us

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.ProfileMenu
import com.jcodonuts.app.data.local.ProfileMenuHeader
import com.jcodonuts.app.data.local.SocmedMenu
import com.jcodonuts.app.profileMenu
import com.jcodonuts.app.profileMenuHeader
import com.jcodonuts.app.socmedMenu
import com.jcodonuts.app.ui.main.profile.ProfileControllerListener

/**
 * Showcases [EpoxyController] with sticky header support
 */
class ContactUsController(
    private val listener: ProfileControllerListener
) : TypedEpoxyController<List<BaseCell>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    override fun buildModels(data: List<BaseCell>?) {
        data?.forEachIndexed() { index, cellData ->
            when(cellData) {
                is ProfileMenuHeader -> addProfileMenuHeader(cellData)
                is ProfileMenu -> addProfileMenu(cellData, index, listener)
                is SocmedMenu -> addSocmedMenu(cellData, index, listener)
            }
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

    private fun addSocmedMenu(cellData:SocmedMenu, index:Int, listener:ProfileControllerListener){
        socmedMenu {
            id("pickup_address")
            data(cellData)
            index(index)
            listener(listener)
        }
    }

}
