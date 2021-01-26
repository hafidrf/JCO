package com.jcodonuts.app.di.module

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jcodonuts.app.App
import com.jcodonuts.app.BuildConfig
import com.jcodonuts.app.data.remote.api.JcoApi
import com.jcodonuts.app.data.remote.api.JcoUserApi
import com.jcodonuts.app.data.remote.api.NewsApi
import com.jcodonuts.app.di.scope.ApplicationScope
import com.jcodonuts.app.utils.SharedPreference
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @ApplicationScope
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()
        return gsonBuilder.create()
    }

    @Provides
    @ApplicationScope
    fun provideHttpCache(application: Application): Cache {
        val cacheSize: Long = 10 * 10 * 1024
        return Cache(application.cacheDir, cacheSize)
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient (cache: Cache, sharedPreference: SharedPreference): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
        client.connectTimeout(1, TimeUnit.MINUTES)
        client.writeTimeout(1, TimeUnit.MINUTES)
        client.readTimeout(1, TimeUnit.MINUTES)
        client.cache(cache)
        client.addNetworkInterceptor(loggingInterceptor)
        client.addInterceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .header("Authorization", BuildConfig.AUTHORIZATION)

            sharedPreference.getValueString(SharedPreference.ACCESS_TOKEN)?.let {
                requestBuilder.addHeader("accessToken", it)
            }

            sharedPreference.getValueString(SharedPreference.REFRESH_TOKEN)?.let {
                requestBuilder.addHeader("x-refreshToken", it)
            }

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return client.build()
    }

    @ApplicationScope
    @Provides
    @Named("provideRetrofit")
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @ApplicationScope
    @Provides
    @Named("provideRetrofitUser")
    fun provideRetrofitUser(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_USER)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @ApplicationScope
    @Provides
    fun provideMovieAPI(@Named("provideRetrofit") retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @ApplicationScope
    @Provides
    fun provideJcoAPI(@Named("provideRetrofit") retrofit: Retrofit): JcoApi {
        return retrofit.create(JcoApi::class.java)
    }

    @ApplicationScope
    @Provides
    fun provideJcoAPIUser(@Named("provideRetrofitUser") retrofit: Retrofit): JcoUserApi {
        return retrofit.create(JcoUserApi::class.java)
    }
}