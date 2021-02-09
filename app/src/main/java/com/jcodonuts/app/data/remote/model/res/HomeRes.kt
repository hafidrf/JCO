package com.jcodonuts.app.data.remote.model.res

data class HomeRes(
    val status: Int,
    val message: String,
    val user: User,
    val pickup_from: List<PickupFrom>,
    val promos: List<Promo>,
    val category: List<Category>,
    val products: List<Product>
)

data class PickupFrom(
    val outlet_code: String,
    val outlet_name: String
)

data class Category(
    val category_name: String,
    val category_title: String,
    val category_img: String,
    val material: String,
    val is_order: String,
    val is_show: String,
    val is_promo: String
)

data class Product(
    val menu_code: String,
    val menu_ordering: String,
    val category_name: String,
    val category_title: String,
    val menu_name: String,
    val menu_desc: String,
    val menu_image: String,
    val min_quantity: String,
    val max_quantity: String,
    val start_date: String,
    val end_date: String,
    val is_promo: String,
    val is_active: String,
    val is_freedelivery: String,
    val menu_amount: String,
    val menu_price: String,
    val menu_URL: String,
    val is_favorite: String
)

data class Promo(
    val menu_code: String,
    val menu_ordering: String,
    val category_name: String,
    val category_title: String,
    val menu_name: String,
    val menu_desc: String,
    val menu_image: String,
    val min_quantity: String,
    val max_quantity: String,
    val start_date: String,
    val end_date: String,
    val is_promo: String,
    val is_active: String,
    val is_freedelivery: String,
    val menu_amount: String,
    val menu_price: String,
    val menu_URL: String
)

data class User(
    val member_name: String,
    val member_point: String
)

data class ProductsByCategoryRes(
    val `data`: List<Product>,
    val status_code: Int
)
