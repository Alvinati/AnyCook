package com.porto.anycook.injection

import android.app.Application
import com.porto.anycook.MainApplication
import com.porto.anycook.injection.module.ActivityBuilderModule
import com.porto.anycook.injection.module.FragmentBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class
    ],
    dependencies = [
        CoreComponent::class
    ])
interface ApplicationComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application) : Builder

        fun coreComponenet(coreComponent: CoreComponent) : Builder

        fun build() : ApplicationComponent
    }

}