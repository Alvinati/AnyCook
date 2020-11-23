package com.porto.core.data.recipe.source

import com.porto.commons.models.Result
import com.porto.core.data.recipe.entity.RecipeEntity

interface RecipeRemoteDataSource {
    suspend fun getRecipes(size: Int, page: Int) : Result<List<RecipeEntity>>
    suspend fun getRecipeDetails(recipeId: Int) : Result<RecipeEntity>
}
