package com.porto.anycook.injection.module.vm

import androidx.lifecycle.ViewModel
import com.porto.anycook.injection.module.ViewModelModule
import com.porto.anycook.injection.vmf.ViewModelKey
import com.porto.anycook.ui.main.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module(includes = [ViewModelModule::class])
interface HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel) : ViewModel

}