package com.jcodonuts.app.data.local

data class ModelOrder(val name:String, val desc:String, val time:String, val price:String, val status:String, val color:String):BaseCell()

data class  ModelOrderHeader(val date:String):BaseCell()
