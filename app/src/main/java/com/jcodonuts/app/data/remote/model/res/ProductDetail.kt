package com.jcodonuts.app.data.remote.model.res

data class ProductDetailRes(
    val `data`: ProductDetail,
    val message: String,
    val status: String
)

data class ProductDetail(
    val category_name: String,
    val id_menu: String,
    var is_favorite: String,
    val is_freedelivery: String,
    val is_promo: String,
    val material: String,
    val menu_amount: String,
    val menu_code: String,
    val menu_desc: String,
    val menu_image: String,
    val menu_name: String,
    val menu_price: String,
    val min_quantity: String,
    val product_id: String,
    val variant_group: List<VariantGroup>
)

data class VariantGroup(
    val jco_id_menu: String,
    val jco_menu_code: String,
    val limit: String,
    val optional: String,
    val product_id: String,
    val variant: List<Variant>,
    val variant_group_desc: String,
    val variant_group_id: String,
    val variant_group_name: String,
    val variant_group_type: String
)

data class Variant(
    val variant_group_id: String,
    val variant_id: String,
    val variant_img: String,
    val variant_name: String,
    val variant_price: String
)