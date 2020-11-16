package com.jcodonuts.app.data.local

data class Countries(
    val countries: List<Country>
)

data class Country(
    val code: String,
    val image: String,
    val name: String
)