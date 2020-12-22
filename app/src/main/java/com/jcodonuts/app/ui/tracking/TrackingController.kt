package com.jcodonuts.app.ui.tracking

import android.util.Log
import android.view.View
import com.airbnb.epoxy.AsyncEpoxyController
import com.jcodonuts.app.*
import com.jcodonuts.app.data.local.*

class TrackingController() : AsyncEpoxyController() {
    private val TAG = "TopupController"

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEach { cellData ->
            when(cellData) {
                is TrackingHeader -> addTrackingHeader(cellData)
                is TrackingStatus -> addTrackingStatus(cellData)
                is TrackingProgress -> addTrackingProgress(cellData)
            }
        }
    }

    private fun addTrackingHeader(cellData: TrackingHeader){
        trackingHeader {
            id(cellData.hashCode())
            data(cellData)
        }
    }
    private fun addTrackingStatus(cellData: TrackingStatus){
        trackingStatus {
            id(cellData.hashCode())
            data(cellData)
        }
    }private fun addTrackingProgress(cellData: TrackingProgress){
        trackingProgress {
            id(cellData.hashCode())
            data(cellData)
        }
    }
}