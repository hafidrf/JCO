package com.jcodonuts.app.data.repository

import android.app.Application
import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jcodonuts.app.data.local.TopupItem
import io.reactivex.Single
import javax.inject.Inject

interface PaymentRepository {
    fun getTopupMethod(): Single<List<TopupItem>>
}

class PaymentRepositoryImpl @Inject constructor(
        private val application: Application
): PaymentRepository, BaseRepository() {


    override fun getTopupMethod(): Single<List<TopupItem>> {
        return composeSingle {
            Single.fromCallable {
                val jsonString = application.baseContext.assets.readFile("topup.json")
                val itemType = object : TypeToken<List<TopupItem>>() {}.type
                Gson().fromJson(jsonString, itemType)
            }
        }
    }

    private fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }


}