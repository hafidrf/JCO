package com.jcodonuts.app.ui.product_detail

import com.airbnb.epoxy.AsyncEpoxyController
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.Divider16
import com.jcodonuts.app.data.local.ProductDetailContent
import com.jcodonuts.app.data.local.ProductDetailDonut
import com.jcodonuts.app.divider16
import com.jcodonuts.app.productDetailContents
import com.jcodonuts.app.productDetailDonutItem

class ProductDetailController(private val listener:ProductDetailControllerListener) :
    AsyncEpoxyController() {
    private val TAG = "TopupController"

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEachIndexed { index, cellData ->
            when(cellData) {
                is ProductDetailContent -> addProductDetailContent(cellData, listener, index)
                is ProductDetailDonut -> addProductDetailDonut(cellData, listener, index)
                is Divider16 -> addDivider16(cellData)
            }
        }
    }

    private fun addDivider16(cellData: Divider16){
        divider16 {
            id(cellData.hashCode())
        }
    }

    private fun addProductDetailContent(cellData: ProductDetailContent, listener:ProductDetailControllerListener, index:Int){
        productDetailContents {
            id("content")
            data(cellData)
            onClickListener(listener)
            index(index)
        }
    }

    private fun addProductDetailDonut(cellData: ProductDetailDonut, listener:ProductDetailControllerListener, index:Int){
        productDetailDonutItem {
            id(cellData.imgURL)
            data(cellData)
            onClickListener(listener)
            index(index)
        }
    }
}