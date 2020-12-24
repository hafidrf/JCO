package com.jcodonuts.app.data.local

data class HomeMenuItems(
    val homeMenuItems: List<HomeMenuItem>
):BaseCell()

data class HomeMenuItem(
    val name: String,
    val imgURL: String,
    val price: String,
    val isStartFrom: Boolean,
    var isPromo: Boolean,
    var isFreeDelivery: Boolean,
    var isFavorite: Boolean
):BaseCell()