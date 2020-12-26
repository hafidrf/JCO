package com.jcodonuts.app.di.module

import com.jcodonuts.app.ui.MainActivity
import com.jcodonuts.app.ui.TestingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [NavHostModule::class])
    abstract fun mainActivityInjector(): MainActivity

    @ContributesAndroidInjector()
    abstract fun testingActivityInjector(): TestingActivity

}