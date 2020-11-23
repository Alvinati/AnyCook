package com.porto.anycook.framework.local.user

import android.content.SharedPreferences
import com.porto.core.data.user.entity.UserEntity
import com.porto.core.data.user.source.UserLocalDataSource
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences)
    : UserLocalDataSource {

    companion object {
        private const val KEY_USER_ID = "UserLocalDataSourceImpl.KeyUserId"
        private const val KEY_USER_NAME = "UserLocalDataSourceImpl.KeyUserName"
        private const val KEY_USER_EMAIL = "UserLocalDataSourceImpl.KeyUserEmail"
        private const val KEY_USER_PHONE = "UserLocalDataSourceImpl.KeyUserPhone"
        private const val KEY_USER_PROFILE_IMAGE_URL = "UserLocalDataSourceImpl.KeyUserProfileImageUrl"
    }

    override fun setCurrentUser(user: UserEntity) {

        sharedPreferences.edit()
                .putInt(KEY_USER_ID, user.id ?: 0)
                .putString(KEY_USER_NAME, user.name)
                .putString(KEY_USER_EMAIL, user.email)
                .putString(KEY_USER_PHONE, user.phoneNumber)
                .putString(KEY_USER_PROFILE_IMAGE_URL, user.imageUrl)
                .apply()
    }

    override fun getCurrentUser(): UserEntity {
        return UserEntity(
                id = sharedPreferences.getInt(KEY_USER_ID, 0),
                name = sharedPreferences.getString(KEY_USER_NAME, "").orEmpty(),
                email = sharedPreferences.getString(KEY_USER_EMAIL, "").orEmpty(),
                phoneNumber = sharedPreferences.getString(KEY_USER_PHONE, "").orEmpty(),
                imageUrl = sharedPreferences.getString(KEY_USER_PROFILE_IMAGE_URL, "").orEmpty()
        )
    }

    override fun clearUserData() {
        sharedPreferences.edit()
                .remove(KEY_USER_ID)
                .remove(KEY_USER_NAME)
                .remove(KEY_USER_EMAIL)
                .remove(KEY_USER_PHONE)
                .remove(KEY_USER_PROFILE_IMAGE_URL)
                .apply()
    }
}