package com.porto.anycook.injection.module

import com.porto.anycook.injection.module.vm.AccountViewModelModule
import com.porto.anycook.injection.module.vm.HomeViewModelModule
import com.porto.anycook.injection.module.vm.LoginViewModelModule
import com.porto.anycook.injection.module.vm.RecipeDetailViewModelModule
import com.porto.anycook.ui.login.LoginFragment
import com.porto.anycook.ui.main.account.AccountFragment
import com.porto.anycook.ui.main.home.HomeFragment
import com.porto.anycook.ui.recipe_detail.RecipeDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [LoginViewModelModule::class])
    abstract fun bindLoginFragment() : LoginFragment

    @ContributesAndroidInjector(modules = [HomeViewModelModule::class])
    abstract fun bindHomeFragment() : HomeFragment

    @ContributesAndroidInjector(modules = [AccountViewModelModule::class])
    abstract fun bindAccountFragment() : AccountFragment

    @ContributesAndroidInjector(modules = [RecipeDetailViewModelModule::class])
    abstract fun bindRecipeDetailFragment() : RecipeDetailFragment
}