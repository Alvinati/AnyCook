package com.porto.anycook.injection.module

import com.porto.anycook.MainActivity
import com.porto.anycook.injection.module.vm.MainViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MainViewModelModule::class])
    abstract fun bindMainActivity() : MainActivity
}