package com.jcodonuts.app.data.repository

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import com.jcodonuts.app.data.local.Countries
import com.jcodonuts.app.data.local.Country
import com.jcodonuts.app.data.local.ProductDetail
import io.reactivex.Single
import javax.inject.Inject

interface ProductRepository {
    fun getDetail(): Single<ProductDetail>
}

class ProductRepositoryImpl @Inject constructor(
        private val application: Application
        ): ProductRepository, BaseRepository() {

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