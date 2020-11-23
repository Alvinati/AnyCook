package com.porto.anycook.injection.module.vm

import androidx.lifecycle.ViewModel
import com.porto.anycook.injection.module.ViewModelModule
import com.porto.anycook.injection.vmf.ViewModelKey
import com.porto.anycook.ui.recipe_detail.RecipeDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface RecipeDetailViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(RecipeDetailViewModel::class)
    fun bindRecipeDetailVM(viewModel: RecipeDetailViewModel) : ViewModel
}