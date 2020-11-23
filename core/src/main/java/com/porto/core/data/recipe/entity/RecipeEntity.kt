package com.porto.core.data.recipe.entity

import com.porto.core.domain.common.module.ImageItem
import com.porto.core.domain.recipe.models.Ingredients
import com.porto.core.domain.recipe.models.Recipe

data class RecipeEntity(
        val id: Int,
        val name : String,
        val thumbnailUrl : String,
        val serveMinutes : Int,
        val countLike : Int,
        var cookingSteps : String?,
        var ingredients: List<Ingredients>?,
        var imageUrls : List<ImageItem>?
) {
    companion object {
        fun create(recipe: Recipe) = RecipeEntity(
                id = recipe.id,
                name = recipe.name,
                thumbnailUrl = recipe.thumbnailUrl,
                serveMinutes = recipe.serveMinutes,
                countLike = recipe.countLike,
                imageUrls = recipe.imageUrls,
                ingredients = recipe.ingredients,
                cookingSteps = recipe.cookingSteps
        )
    }

    fun toRecipe() : Recipe {
        return Recipe(
                id = id,
                name = name,
                thumbnailUrl = thumbnailUrl,
                serveMinutes = serveMinutes,
                countLike = countLike,
                imageUrls = imageUrls,
                ingredients = ingredients,
                cookingSteps = cookingSteps
        )
    }

}