package com.porto.anycook.injection.module

import android.content.Context
import android.content.SharedPreferences
import com.porto.anycook.R
import com.porto.anycook.framework.local.authentication.AuthenticationLocalDataSourceImpl
import com.porto.anycook.framework.local.user.UserLocalDataSourceImpl
import com.porto.core.data.authentication.source.AuthenticationLocalDataSource
import com.porto.core.data.user.source.UserLocalDataSource
import com.porto.core.domain.authentication.repository.AuthenticationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class CoreModule {

    @Singleton
    @Provides
    fun providedSharedPreference(context: Context) : SharedPreferences {
        return context.getSharedPreferences(
            context.getString(R.string.shared_pref_key),
            Context.MODE_PRIVATE
        )
    }
}