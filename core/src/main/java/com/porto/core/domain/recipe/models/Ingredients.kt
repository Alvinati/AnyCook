package com.porto.core.domain.recipe.models

data class Ingredients(
    val id : Int,
    val name : String,
    val imageUrl : String?,
    val amountNeeded : String?
)