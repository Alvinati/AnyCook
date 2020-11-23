package com.porto.core.domain.recipe.models

import com.porto.core.domain.common.module.ImageItem
import com.porto.core.domain.recipe.models.Ingredients

data class Recipe(
        val id: Int,
        val name : String,
        val thumbnailUrl : String,
        val serveMinutes : Int,
        val serveMinutesText : String = "$serveMinutes minutes",
        val countLike : Int,
        val countLikeText : String = "$countLike likes",
        var imageUrls : List<ImageItem>?,
        var ingredients: List<Ingredients>?,
        var cookingSteps : String?
)