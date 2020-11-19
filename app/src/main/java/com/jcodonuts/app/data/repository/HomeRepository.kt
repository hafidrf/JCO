package com.jcodonuts.app.data.repository

import android.app.Application
import android.content.res.AssetManager
import com.google.gson.Gson
import com.jcodonuts.app.data.local.HomePromos
import com.jcodonuts.app.data.remote.api.NewsApi
import io.reactivex.Single
import javax.inject.Inject

interface HomeRepository {
    fun getPromo(): Single<HomePromos>
}

class HomeRepositoryImpl @Inject constructor(
    private val service: NewsApi,
    private val application:Application
): HomeRepository, BaseRepository() {

    override fun getPromo(): Single<HomePromos> {
        return composeSingle {
            Single.fromCallable {
                val jsonString = application.baseContext.assets.readFile("homePromo.json")
                Gson().fromJson(jsonString, HomePromos::class.java)
            }
        }
    }

    private fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }

}