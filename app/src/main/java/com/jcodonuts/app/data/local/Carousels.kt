package com.jcodonuts.app.data.local

data class Carousels(
    val carousels: List<Carousel>
):BaseCell()

data class Carousel(
    val imgURL: String
)