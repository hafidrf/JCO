package com.jcodonuts.app.data.local

data class HomeMenuItems(
    val carousels: List<HomeMenuItem>
):BaseCell()

data class HomeMenuItem(
    val name: String,
    val imgURL: String,
    val price: String,
    val isStartFrom: Boolean,
    val isPromo: Boolean
)