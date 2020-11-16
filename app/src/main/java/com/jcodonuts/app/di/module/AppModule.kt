package com.jcodonuts.app.di.module

import com.jcodonuts.app.di.scope.ApplicationScope
import com.jcodonuts.app.utils.AppSchedulerProvider
import com.jcodonuts.app.utils.SchedulerProvider
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    @ApplicationScope
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

}