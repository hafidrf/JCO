package com.jcodonuts.app

import android.content.Context
import androidx.multidex.MultiDex
import com.jcodonuts.app.di.component.DaggerAppComponent
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    private val applicationInjector = DaggerAppComponent.builder().application(this).build()

    override fun applicationInjector() = applicationInjector

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this);
    }
}