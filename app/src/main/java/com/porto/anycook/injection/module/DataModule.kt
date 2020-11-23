package com.porto.anycook.injection.module

import com.porto.core.data.authentication.AuthenticationRepositoryImpl
import com.porto.core.data.authentication.source.AuthenticationLocalDataSource
import com.porto.core.data.authentication.source.AuthenticationRemoteDataSource
import com.porto.core.data.recipe.RecipeRepositoryImpl
import com.porto.core.data.recipe.source.RecipeRemoteDataSource
import com.porto.core.data.remote.RecipeRemoteDataSourceImpl
import com.porto.core.data.user.UserRepositoryImpl
import com.porto.core.data.user.source.UserLocalDataSource
import com.porto.core.data.user.source.UserRemoteDataSource
import com.porto.core.domain.authentication.repository.AuthenticationRepository
import com.porto.core.domain.recipe.repository.RecipeRepository
import com.porto.core.domain.user.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RemoteModule::class, CacheModule::class])
class DataModule {

    @Singleton
    @Provides
    fun provideAuthRepo(localDataSource: AuthenticationLocalDataSource, remoteDataSource: AuthenticationRemoteDataSource)
            : AuthenticationRepository {
        return AuthenticationRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Singleton
    @Provides
    fun provideUserRepo(localDataSource: UserLocalDataSource, remoteDataSource: UserRemoteDataSource)
            : UserRepository {
        return UserRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Singleton
    @Provides
    fun provideRecipeRepo(recipeRemoteDataSource: RecipeRemoteDataSource) : RecipeRepository {
        return RecipeRepositoryImpl(recipeRemoteDataSource)
    }
}