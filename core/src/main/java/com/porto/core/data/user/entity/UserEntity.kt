package com.porto.core.data.user.entity

import com.porto.core.domain.user.models.User

data class UserEntity(
    val id : Int?,
    val name : String,
    val email : String,
    val phoneNumber: String,
    val imageUrl : String?
) {
    companion object{
        fun create(user: User) = UserEntity(
            id = user.id,
            name = user.name,
            email = user.email,
            phoneNumber = user.phoneNumber,
            imageUrl = user.avatarUrl
        )
    }
    fun toUser() : User {
        return User (
            id = id ?: 0,
            name = name,
            email = email,
            phoneNumber = phoneNumber,
            avatarUrl = imageUrl
        )
    }
}