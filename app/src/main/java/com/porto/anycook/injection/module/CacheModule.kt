package com.porto.anycook.injection.module

import android.content.SharedPreferences
import com.porto.anycook.framework.local.authentication.AuthenticationLocalDataSourceImpl
import com.porto.anycook.framework.local.user.UserLocalDataSourceImpl
import com.porto.core.data.authentication.source.AuthenticationLocalDataSource
import com.porto.core.data.user.source.UserLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface CacheModule {

   @Binds
    fun bindAuthLocalDataSource(impl: AuthenticationLocalDataSourceImpl) :
            AuthenticationLocalDataSource

    @Binds
    fun bindsUserLocalDataSource(impl: UserLocalDataSourceImpl) :
            UserLocalDataSource

}