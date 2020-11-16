package com.jcodonuts.app.data.repository

import com.jcodonuts.app.BuildConfig
import com.jcodonuts.app.data.remote.api.NewsApi
import com.jcodonuts.app.data.remote.helper.ErrorNetworkHandler
import com.jcodonuts.app.data.remote.model.News
import io.reactivex.Single
import javax.inject.Inject

interface NewsRepository {
    fun getNews(country:String,
                page:String): Single<News>
}

class NewsRepositoryImpl @Inject constructor(
    private val service: NewsApi
): NewsRepository, BaseRepository() {

    override fun getNews(
        country: String,
        page: String
    ): Single<News> {
        return composeSingle { service.getNews(country, BuildConfig.API_KEY, "technology", "5", page) }
            .compose(ErrorNetworkHandler())
    }

}