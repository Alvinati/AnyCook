package com.porto.anycook.injection.module

import androidx.lifecycle.ViewModelProvider
import com.porto.anycook.injection.vmf.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory
}