package com.jcodonuts.app.data.remote.model.req

data class HomeReq(
    val brand: Int,
    val city: String,
    val member_id: String,
    val latitude: String,
    val longitude: String,
    val radius: String
)
