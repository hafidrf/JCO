package com.jcodonuts.app.data.local

data class HomePromos(
    val promos: List<PromoBanner>
):BaseCell()

data class PromoBanner(
    val imgURL: String
)