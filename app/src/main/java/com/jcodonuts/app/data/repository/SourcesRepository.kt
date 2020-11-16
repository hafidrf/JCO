package com.jcodonuts.app.data.repository

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import com.jcodonuts.app.data.local.Countries
import com.jcodonuts.app.data.local.Country
import io.reactivex.Single
import javax.inject.Inject

interface MenuRepository {
    fun getMenus(context: Context): Single<List<Country>>
}

class MenuRepositoryImpl @Inject constructor(): MenuRepository, BaseRepository() {

    override fun getMenus(context:Context): Single<List<Country>> {

        return composeSingle {
            Single.fromCallable {
                val jsonString = context.applicationContext.assets.readFile("countries.json")
                Gson().fromJson(jsonString, Countries::class.java).countries
            }
        }
    }

    private fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }

}