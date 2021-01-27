package com.jcodonuts.app.ui.tracking

import com.airbnb.epoxy.AsyncEpoxyController
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.trackingFooter
import com.jcodonuts.app.trackingHeader
import com.jcodonuts.app.trackingProgress
import com.jcodonuts.app.trackingStatus

class TrackingController(val listener: TrackingControllerListener) : AsyncEpoxyController() {
    private val TAG = "TopupController"

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEach { cellData ->
            when(cellData) {
                is TrackingHeader -> addTrackingHeader(cellData, listener)
                is TrackingStatus -> addTrackingStatus(cellData)
                is TrackingProgress -> addTrackingProgress(cellData)
                is TrackingFooter -> addTrackingFooter(cellData, listener)
            }
        }
    }

    private fun addTrackingHeader(cellData: TrackingHeader, listener: TrackingControllerListener){
        trackingHeader {
            id(cellData.hashCode())
            data(cellData)
            listener(listener)
        }
    }

    private fun addTrackingStatus(cellData: TrackingStatus){
        trackingStatus {
            id(cellData.hashCode())
            data(cellData)
        }
    }

    private fun addTrackingProgress(cellData: TrackingProgress){
        trackingProgress {
            id(cellData.hashCode())
            data(cellData)
        }
    }

    private fun addTrackingFooter(cellData: TrackingFooter, listener: TrackingControllerListener){
        trackingFooter {
            id(cellData.hashCode())
            data(cellData)
            listener(listener)
        }
    }
}