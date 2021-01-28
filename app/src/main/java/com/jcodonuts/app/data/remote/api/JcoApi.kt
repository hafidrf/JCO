package com.jcodonuts.app.data.remote.api

import com.jcodonuts.app.data.remote.model.req.HomeReq
import com.jcodonuts.app.data.remote.model.req.ProductByCategoryReq
import com.jcodonuts.app.data.remote.model.req.ProductFavoriteReq
import com.jcodonuts.app.data.remote.model.res.HomeRes
import com.jcodonuts.app.data.remote.model.res.ProductsByCategoryRes
import com.jcodonuts.app.data.remote.model.res.SuccessRes
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface JcoApi {

    @POST("home/app")
    fun fetchAppHome(
        @Body body:HomeReq
    ): Single<HomeRes>

    @POST("product/category")
    fun getProductByCategory(
        @Body body:ProductByCategoryReq
    ): Single<ProductsByCategoryRes>

    @POST("product/favorite")
    fun setProductFavorite(
        @Body body:ProductFavoriteReq
    ): Single<SuccessRes>
}