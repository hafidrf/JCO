package com.jcodonuts.app.data.local


data class ProductDetail(
    val content: ProductDetailContent,
    val donuts: List<ProductDetailDonut>
)

data class ProductDetailContent(
    val colorType: String,
    val colorTypeText: String,
    val desc: String,
    val maxDesc: String,
    val price: String,
    val productName: String,
    var quantity: Int,
    var quantityInPcs: Int,
    var totalPerPack: Int,
    val type: String
):BaseCell()

data class ProductDetailDonut(
    val imgURL: String,
    val name: String,
    val price: String,
    var quantity: Int
):BaseCell()