package com.jcodonuts.app.di.module

import android.app.Application
import com.jcodonuts.app.App
import com.jcodonuts.app.data.remote.api.JcoApi
import com.jcodonuts.app.data.remote.api.JcoUserApi
import com.jcodonuts.app.data.remote.api.NewsApi
import com.jcodonuts.app.data.repository.*
import com.jcodonuts.app.di.scope.ApplicationScope
import com.jcodonuts.app.utils.SharedPreference
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideSharePref(app:Application): SharedPreference {
        return SharedPreference(app.applicationContext)
    }

    @Provides
    @ApplicationScope
    fun provideCountriesRepository(): MenuRepository {
        return MenuRepositoryImpl()
    }

    @Provides
    @ApplicationScope
    fun provideNewsRepository(service:NewsApi): NewsRepository {
        return NewsRepositoryImpl(service)
    }

    @Provides
    @ApplicationScope
    fun provideHomeRepository(service:JcoApi, application: Application): HomeRepository {
        return HomeRepositoryImpl(service, application)
    }

    @Provides
    @ApplicationScope
    fun provideTopupRepository(application: Application): PaymentRepository {
        return PaymentRepositoryImpl(application)
    }

    @Provides
    @ApplicationScope
    fun provideProductRepository(application: Application): ProductRepository {
        return ProductRepositoryImpl(application)
    }

    @Provides
    @ApplicationScope
    fun providePickupRepository(application: Application): PickupRepository {
        return PickupRepositoryImpl(application)
    }

    @Provides
    @ApplicationScope
    fun provideDeliveryRepository(application: Application): DeliveryRepository {
        return DeliveryRepositoryImpl(application)
    }

    @Provides
    @ApplicationScope
    fun provideAuthRepository(service:JcoUserApi): AuthRepository {
        return AuthRepositoryImpl(service)
    }
}