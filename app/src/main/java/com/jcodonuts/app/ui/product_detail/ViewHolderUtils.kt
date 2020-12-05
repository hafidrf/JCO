//package com.jcodonuts.app.ui.product_detail
//
//import com.jcodonuts.app.utils.recycler_adapter.RecyclerItem
//import com.jcodonuts.app.BR
//import com.jcodonuts.app.R
//import com.jcodonuts.app.data.local.Divider16
//import com.jcodonuts.app.data.local.ProductDetailContent
//import com.jcodonuts.app.data.local.ProductDetailDonut
//
////interface ViewHolderListener{
////    fun onPlusClick(data: ProductDetailContent)
////    fun onMinusClick(data: ProductDetailContent)
////    fun onDonutPlusClick(data: ProductDetailDonut)
////    fun onDonutMinusClick(data: ProductDetailDonut)
////}
//
//fun ProductDetailContent.toRecyclerItem(listener: ViewHolderListener) = RecyclerItem(
//    data = listOf(this, listener),
//    layoutId = R.layout.view_holder_product_detail_contents,
//    variableId = listOf(BR.data, BR.onClickListener)
//)
//
//fun ProductDetailDonut.toRecyclerItem(listener: ViewHolderListener) = RecyclerItem(
//    data = listOf(this, listener),
//    layoutId = R.layout.view_holder_product_detail_donut_item,
//    variableId = listOf(BR.data, BR.onClickListener)
//)
//
//fun Divider16.toRecyclerItem() = RecyclerItem(
//    data = listOf(this),
//    layoutId = R.layout.view_holder_divider16,
//    variableId = listOf(0)
//)