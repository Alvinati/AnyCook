package com.porto.core.data.user.source

import com.porto.core.data.user.entity.UserEntity

interface UserLocalDataSource {
    fun setCurrentUser(user : UserEntity)
    fun getCurrentUser() : UserEntity
    fun clearUserData()
}