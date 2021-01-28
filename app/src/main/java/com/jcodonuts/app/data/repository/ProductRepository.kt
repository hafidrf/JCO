package com.jcodonuts.app.data.repository

import android.app.Application
import android.content.res.AssetManager
import com.google.gson.Gson
import com.jcodonuts.app.data.local.ProductDetail
import com.jcodonuts.app.data.remote.api.JcoApi
import com.jcodonuts.app.data.remote.helper.ErrorNetworkHandler
import com.jcodonuts.app.data.remote.model.req.ProductDetailReq
import com.jcodonuts.app.data.remote.model.res.ProductDetailRes
import io.reactivex.Single
import javax.inject.Inject

interface ProductRepository {
    fun getDetail(): Single<ProductDetail>
    fun getProductDetail(body: ProductDetailReq): Single<ProductDetailRes>
}

class ProductRepositoryImpl @Inject constructor(
    private val service: JcoApi,
    private val application: Application
): ProductRepository, BaseRepository() {

    override fun getProductDetail(body: ProductDetailReq): Single<ProductDetailRes> {
        return composeSingle { service.getProductDetail(body) }
            .compose(ErrorNetworkHandler())
    }





    private fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }

    override fun getDetail(): Single<ProductDetail> {
        return composeSingle {
            Single.fromCallable {
                val jsonString = application.baseContext.assets.readFile("product_detail.json")
                Gson().fromJson(jsonString, ProductDetail::class.java)
            }
        }
    }

}