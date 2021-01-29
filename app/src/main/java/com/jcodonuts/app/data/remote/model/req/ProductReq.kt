package com.jcodonuts.app.data.remote.model.req

data class ProductFavoriteReq(
    val favorite: String,
    val member_id: String,
    val menu_code: String
)

data class ProductByCategoryReq(
    val category: String,
    val city: String,
    val member_id: String
)

data class ProductDetailReq(
    val brand: String,
    val city: String,
    val member_id: String,
    val menu_id: String
)