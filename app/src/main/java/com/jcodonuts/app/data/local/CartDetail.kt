package com.jcodonuts.app.data.local

data class CartDetail(val paymentName:String,
                      var paymentIcon:String,
                      var price:String,
                      var deliveryService:String,
                      var totalPrice:String,
                      ):BaseCell()
