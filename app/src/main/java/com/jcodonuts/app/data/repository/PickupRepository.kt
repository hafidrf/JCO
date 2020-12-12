package com.jcodonuts.app.data.repository

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jcodonuts.app.data.local.PickupItem
import io.reactivex.Single
import javax.inject.Inject

interface PickupRepository {
    fun getListLocations(): Single<List<PickupItem>>
}

class PickupRepositoryImpl @Inject constructor(
        private val application: Application
): PickupRepository, BaseRepository() {


    override fun getListLocations(): Single<List<PickupItem>> {
        return composeSingle {
            Single.fromCallable {
                val jsonString = application.baseContext.assets.readFile("pickup_places.json")
                val itemType = object : TypeToken<List<PickupItem>>() {}.type
                Gson().fromJson(jsonString, itemType)
            }
        }
    }

    private fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }


}