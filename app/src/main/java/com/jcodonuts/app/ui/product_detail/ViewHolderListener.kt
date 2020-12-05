package com.jcodonuts.app.ui.product_detail

interface ViewHolderListener {
    fun onPlusClick(position:Int)
    fun onMinusClick(position:Int)
    fun onDonutPlusClick(position:Int)
    fun onDonutMinusClick(position:Int)
}