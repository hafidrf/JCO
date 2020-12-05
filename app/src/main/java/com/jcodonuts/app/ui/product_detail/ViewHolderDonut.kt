//package com.jcodonuts.app.ui.product_detail
//
//import com.jcodonuts.app.utils.recycler_adapter.RecyclerItem
//import com.jcodonuts.app.BR
//import com.jcodonuts.app.R
//import com.jcodonuts.app.data.local.ProductDetailDonut
//
//class ViewHolderDonut(val content: ProductDetailDonut, val listener: ViewHolderDonutListener) {
//
//    fun onPlusClick(){
//        listener.onDonutPlusClick(content)
//    }
//
//    fun onMinusClick(){
//        listener.onDonutMinusClick(content)
//    }
//
//    companion object{
//        fun create(content: ProductDetailDonut, listener: ViewHolderDonutListener): ViewHolderDonut {
//            return ViewHolderDonut(content, listener)
//        }
//    }
//}
//
//interface ViewHolderDonutListener{
//    fun onDonutPlusClick(content: ProductDetailDonut)
//    fun onDonutMinusClick(content: ProductDetailDonut)
//}
//
//fun ViewHolderDonut.toRecyclerItem() = RecyclerItem(
//    data = this,
//    layoutId = R.layout.view_holder_product_detail_donut_item,
//    variableId = BR.data
//)