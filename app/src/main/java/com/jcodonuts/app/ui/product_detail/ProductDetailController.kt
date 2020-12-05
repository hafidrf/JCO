//package com.jcodonuts.app.ui.product_detail
//
//import android.util.Log
//import android.view.View
//import com.airbnb.epoxy.AsyncEpoxyController
//import com.airbnb.epoxy.EpoxyAsyncUtil
//import com.airbnb.epoxy.TypedEpoxyController
//import com.jcodonuts.app.*
//import com.jcodonuts.app.data.local.*
//
//class ProductDetailController(private val listener:ProductDetailControllerListener) : TypedEpoxyController<List<BaseCell>>() {
//    private val TAG = "TopupController"
//
////    var data: List<BaseCell> = emptyList()
////        set(value) {
////            field = value
////            requestModelBuild()
////        }
//
//    override fun buildModels(data: List<BaseCell>) {
//        data.forEach { cellData ->
//            when(cellData) {
//                is ProductDetailContent -> addProductDetailContent(cellData, listener)
//                is ProductDetailDonut -> addProductDetailDonut(cellData, listener)
//                is Divider16 -> addDivider16(cellData)
//            }
//        }
//    }
//
//    private fun addDivider16(cellData: Divider16){
//        divider16 {
//            id(cellData.hashCode())
//        }
//    }
//
//    private fun addProductDetailContent(cellData: ProductDetailContent, listener:ProductDetailControllerListener){
//        productDetailContents {
//            id("content")
//            data(cellData)
//            onItemClick(listener)
//        }
//    }
//
//    private fun addProductDetailDonut(cellData: ProductDetailDonut, listener:ProductDetailControllerListener){
//        productDetailDonutItem {
//            id(cellData.imgURL)
//            data(cellData)
//            onItemClick(listener)
//        }
//    }
//}