package com.jcodonuts.app.data.local

data class ModelTracking(
        val trackingHeader: TrackingHeader,
        val trackingStatus: TrackingStatus,
        val trackingProgress: TrackingProgress
        ):BaseCell()

data class TrackingHeader(
        val noOrder:String,
        val pickup:String,
):BaseCell()

data class TrackingStatus(
        val date:String,
):BaseCell()

data class TrackingProgress(
        val icon:Int,
        val title:String,
        val desc:String,
        val time:String,
        val isComplete:Boolean,
        val position:String,
):BaseCell()
