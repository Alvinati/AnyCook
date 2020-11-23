package com.porto.anycook.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class AnyCookActivity<VM : ViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    lateinit var viewModel: ViewModel

    abstract fun provideViewModel() : VM
}