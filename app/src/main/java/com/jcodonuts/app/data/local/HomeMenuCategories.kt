package com.jcodonuts.app.data.local

data class HomeMenuCategories(
    val homeMenus: List<MenuCategory>
):BaseCell()

data class MenuCategory(
    val title: String,
    val img: Int,
    var isSelected: Boolean,
):BaseCell()