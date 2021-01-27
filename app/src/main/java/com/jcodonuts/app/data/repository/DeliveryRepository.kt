package com.jcodonuts.app.data.repository

import android.app.Application
import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jcodonuts.app.data.local.DeliveryItem
import io.reactivex.Single
import javax.inject.Inject

interface DeliveryRepository {
    fun getListLocations(): Single<List<DeliveryItem>>
}

class DeliveryRepositoryImpl @Inject constructor(
        private val application: Application
): DeliveryRepository, BaseRepository() {


    override fun getListLocations(): Single<List<DeliveryItem>> {
        return composeSingle {
            Single.fromCallable {
                val jsonString = application.baseContext.assets.readFile("pickup_places.json")
                val itemType = object : TypeToken<List<DeliveryItem>>() {}.type
                Gson().fromJson(jsonString, itemType)
            }
        }
    }

    private fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }


}