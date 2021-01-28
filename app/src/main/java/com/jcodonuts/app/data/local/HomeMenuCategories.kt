package com.jcodonuts.app.data.local

data class HomeMenuCategories(
    val homeMenus: List<MenuCategory>
):BaseCell()

data class MenuCategory(
    val name: String,
    val title: String,
    val img: String,
    var isSelected: Boolean,
):BaseCell()