package com.jcodonuts.app.di.module

import com.jcodonuts.app.ui.base.InjectingNavHostFragment
import com.jcodonuts.app.ui.base.InjectingNavHostFragmentMain
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NavHostModule {

    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun navHostFragmentInjector(): InjectingNavHostFragment

    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun navHostFragmentInjectorMain(): InjectingNavHostFragmentMain
}