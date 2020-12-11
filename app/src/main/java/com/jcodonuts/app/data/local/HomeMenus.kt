package com.jcodonuts.app.data.local

data class HomeMenus(
    val homeMenus: List<Menu>
):BaseCell()

data class Menu(
    val title: String,
    val img: Int,
    var isSelected: Boolean,
):BaseCell()