package com.jcodonuts.app.data.repository

import android.app.Application
import android.content.res.AssetManager
import com.google.gson.Gson
import com.jcodonuts.app.App
import com.jcodonuts.app.BuildConfig
import com.jcodonuts.app.data.local.Carousels
import com.jcodonuts.app.data.local.Countries
import com.jcodonuts.app.data.remote.api.NewsApi
import com.jcodonuts.app.data.remote.helper.ErrorNetworkHandler
import com.jcodonuts.app.data.remote.model.News
import io.reactivex.Single
import javax.inject.Inject

interface HomeRepository {
    fun getPromo(): Single<Carousels>
}

class HomeRepositoryImpl @Inject constructor(
    private val service: NewsApi,
    private val application:Application
): HomeRepository, BaseRepository() {

    override fun getPromo(): Single<Carousels> {
        return composeSingle {
            Single.fromCallable {
                val jsonString = application.baseContext.assets.readFile("homePromo.json")
                Gson().fromJson(jsonString, Carousels::class.java)
            }
        }
    }

    private fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }

}