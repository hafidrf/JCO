package com.jcodonuts.app.data.remote.model.req

data class ProductFavoriteReq(
    val favorite: String,
    val member_id: String,
    val menu_code: String
)

data class ProductByCategoryReq(
    val category: String,
    val city: String
)