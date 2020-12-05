//package com.jcodonuts.app.ui.product_detail
//
//import com.jcodonuts.app.utils.recycler_adapter.RecyclerItem
//import com.jcodonuts.app.BR
//import com.jcodonuts.app.R
//import com.jcodonuts.app.data.local.ProductDetailContent
//
//class ViewHolderContent(val content: ProductDetailContent, val listener: ViewHolderContentListener) {
//
//    companion object{
//        fun create(content: ProductDetailContent, listener: ViewHolderContentListener): ViewHolderContent {
//            return ViewHolderContent(content, listener)
//        }
//    }
//}
//
//interface ViewHolderContentListener{
//    fun onPlusClick(content: ProductDetailContent)
//    fun onMinusClick(content: ProductDetailContent)
//}
//
//fun ViewHolderContent.toRecyclerItem() = RecyclerItem(
//    data = this,
//    layoutId = R.layout.view_holder_product_detail_contents,
//    variableId = BR.data
//)