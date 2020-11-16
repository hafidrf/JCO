package com.jcodonuts.app.di.module

import com.jcodonuts.app.data.remote.api.NewsApi
import com.jcodonuts.app.data.repository.MenuRepository
import com.jcodonuts.app.data.repository.MenuRepositoryImpl
import com.jcodonuts.app.data.repository.NewsRepository
import com.jcodonuts.app.data.repository.NewsRepositoryImpl
import com.jcodonuts.app.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

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
}