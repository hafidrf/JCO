package com.jcodonuts.app.data.remote.api

import com.jcodonuts.app.data.remote.model.News
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface JcoUserApi {

    @GET("top-headlines")
    fun getNews(
        @Query("country") country:String,
        @Query("apiKey") apiKey:String,
        @Query("category") category:String,
        @Query("pageSize") pageSize:String,
        @Query("page") page:String
    ): Single<News>
}