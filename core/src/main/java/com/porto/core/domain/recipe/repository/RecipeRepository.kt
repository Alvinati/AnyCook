package com.porto.core.domain.recipe.repository

import com.porto.commons.models.Result
import com.porto.core.domain.recipe.models.Recipe

interface RecipeRepository {
    suspend fun getListRecipe(size: Int, page: Int) : Result<List<Recipe>>
    suspend fun getDetailRecipe(recipeId: Int) : Result<Recipe>
}