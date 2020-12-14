package com.jcodonuts.app.data.local

data class CartProduct(val name: String,
                       val imgURL: String,
                       val price: String,
                       val productType: String,
                       var notes: String,
                       var qty: Int):BaseCell()
