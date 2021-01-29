package com.jcodonuts.app.data.remote.api

import com.jcodonuts.app.data.remote.model.req.LoginReq
import com.jcodonuts.app.data.remote.model.req.RegisterReq
import com.jcodonuts.app.data.remote.model.res.LoginRes
import com.jcodonuts.app.data.remote.model.res.RegisterRes
import com.jcodonuts.app.data.remote.model.res.SuccessRes
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface JcoUserApi {

    @POST("member/login")
    fun login(
        @Body body:LoginReq
    ): Single<LoginRes>

    @POST("member/register")
    fun register(
        @Body body:RegisterReq
    ): Single<RegisterRes>

    @GET("member/logout")
    fun logout(): Single<SuccessRes>
}