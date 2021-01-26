package com.jcodonuts.app.data.remote.api

import com.jcodonuts.app.data.remote.model.News
import com.jcodonuts.app.data.remote.model.req.LoginReq
import com.jcodonuts.app.data.remote.model.res.LoginRes
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface JcoUserApi {

    @POST("member/login")
    fun login(
        @Body body:LoginReq
    ): Single<LoginRes>
}