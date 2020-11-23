package com.porto.core.domain.user.models

data class User(
    val id : Int,
    val name : String,
    val email : String,
    val phoneNumber : String,
    val avatarUrl : String?
)