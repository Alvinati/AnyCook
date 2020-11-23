package com.porto.anycook.injection

import android.content.Context
import android.content.SharedPreferences
import com.porto.anycook.injection.module.CoreModule
import com.porto.core.data.authentication.source.AuthenticationLocalDataSource
import com.porto.core.data.user.source.UserLocalDataSource
import com.porto.core.domain.authentication.repository.AuthenticationRepository
import com.porto.core.domain.recipe.repository.RecipeRepository
import com.porto.core.domain.user.repository.UserRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent : AndroidInjector<Context> {

    fun authRepo() : AuthenticationRepository
    fun userRepo() : UserRepository
    fun recipeRepo() : RecipeRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context) : Builder

        fun build() : CoreComponent
    }
}