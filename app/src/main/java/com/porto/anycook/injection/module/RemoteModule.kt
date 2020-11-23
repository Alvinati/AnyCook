package com.porto.anycook.injection.module

import com.porto.core.data.authentication.source.AuthenticationRemoteDataSource
import com.porto.core.data.recipe.source.RecipeRemoteDataSource
import com.porto.core.data.remote.AuthenticationRemoteDataSourceImpl
import com.porto.core.data.remote.RecipeRemoteDataSourceImpl
import com.porto.core.data.remote.UserRemoteDataSourceImpl
import com.porto.core.data.user.source.UserRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface RemoteModule {

    @Binds
    fun bindAuthenticationRemoteDataSource(impl: AuthenticationRemoteDataSourceImpl)
            : AuthenticationRemoteDataSource

    @Binds
    fun bindUserRemoteDataSource(impl: UserRemoteDataSourceImpl)
            : UserRemoteDataSource

    @Binds
    fun bindRecipeDataSource(impl: RecipeRemoteDataSourceImpl)
            : RecipeRemoteDataSource

}