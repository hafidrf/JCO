package com.jcodonuts.app.data.local


data class PaymentDetail(
    val orderDetail: OrderDetail,
    val orderProducts: List<OrderProduct>,
    val orderTotal: OrderTotal
)

data class OrderDetail(
    val addressType:String,
    val addressName:String,
    val addressDetail:String,
    val addressDistance:String,
    val orderNumber:String,
    val purchaseDate:String
):BaseCell()

data class OrderProduct(
        val name:String,
        val qty:String,
        val notes:String
):BaseCell()

data class OrderTotal(
        val subTotal:String,
        val deliveryFee:String,
        val taskAndService:String,
        val total:String
):BaseCell()