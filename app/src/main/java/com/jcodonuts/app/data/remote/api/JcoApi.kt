package com.jcodonuts.app.data.remote.api

import com.jcodonuts.app.data.remote.model.req.HomeReq
import com.jcodonuts.app.data.remote.model.res.HomeRes
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface JcoApi {

    @POST("home/app")
    fun fetchAppHome(
        @Body body:HomeReq
    ): Single<HomeRes>
}