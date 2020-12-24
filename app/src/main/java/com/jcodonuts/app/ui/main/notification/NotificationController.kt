package com.jcodonuts.app.ui.main.notification

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.jcodonuts.app.*
import com.jcodonuts.app.data.ModelNotification
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.ui.main.home.HomeControllerListener

/**
 * Showcases [EpoxyController] with sticky header support
 */
class NotificationController(
    private val listener: NotificationControllerListener
) : AsyncEpoxyController() {

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEachIndexed { index, cellData ->
            when(cellData) {
                is ModelNotification -> addModelNotification(cellData, listener, index)
            }
        }
    }

    private fun addModelNotification(cellData: ModelNotification, listener: NotificationControllerListener, index:Int) {
        notification {
            id(cellData.hashCode())
            data(cellData)
            index(index)
            listener(listener)
        }
    }

}
