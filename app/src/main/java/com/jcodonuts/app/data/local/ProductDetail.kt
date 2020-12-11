package com.jcodonuts.app.data.local

import com.jcodonuts.app.R
import com.jcodonuts.app.ui.base.BaseModel


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
):BaseModel{
    override fun layoutId(): Int {
        return R.layout.view_holder_product_detail_contents
    }
}

data class ProductDetailDonut(
    val imgURL: String,
    val name: String,
    val price: String,
    var quantity: Int
):BaseModel{
    override fun layoutId(): Int {
        return R.layout.view_holder_product_detail_donut_item
    }
}