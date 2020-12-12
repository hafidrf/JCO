package com.jcodonuts.app.data.local

data class PickupItem(
    val _id: String,
    val address: String,
    val distance: String,
    var isFavorite: Boolean,
    val placeName: String
):BaseCell()