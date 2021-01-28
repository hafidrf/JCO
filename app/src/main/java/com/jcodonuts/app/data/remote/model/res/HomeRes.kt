package com.jcodonuts.app.data.remote.model.res

data class HomeRes(
    val category: List<Category>,
    val message: String,
    val pickup_from: String,
    val products: List<Product>,
    val promos: List<Promo>,
    val status: Int,
    val user: User
)

data class Category(
    val category_img: String,
    val category_name: String,
    val category_title: String,
    val is_order: String,
    val is_promo: String,
    val is_show: String,
    val material: String
)

data class Product(
    val category_name: String,
    val category_title: String,
    val end_date: String,
    val is_active: String,
    val is_favorite: String,
    val is_freedelivery: String,
    val is_promo: String,
    val max_quantity: String,
    val menu_URL: String,
    val menu_amount: String,
    val menu_code: String,
    val menu_desc: String,
    val menu_image: String,
    val menu_name: String,
    val menu_ordering: String,
    val menu_price: String,
    val min_quantity: String,
    val start_date: String
)

data class Promo(
    val category_name: String,
    val category_title: String,
    val end_date: String,
    val is_active: String,
    val is_freedelivery: String,
    val is_promo: String,
    val max_quantity: String,
    val menu_URL: String,
    val menu_amount: String,
    val menu_code: String,
    val menu_desc: String,
    val menu_image: String,
    val menu_name: String,
    val menu_ordering: String,
    val menu_price: String,
    val min_quantity: String,
    val start_date: String
)

data class User(
    val member_name: String,
    val member_point: String
)

data class ProductsByCategoryRes(
    val `data`: List<Product>,
    val status_code: Int
)
