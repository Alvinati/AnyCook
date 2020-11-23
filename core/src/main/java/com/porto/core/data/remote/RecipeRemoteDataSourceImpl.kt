package com.porto.core.data.remote

import com.porto.commons.models.Result
import com.porto.core.data.recipe.entity.RecipeEntity
import com.porto.core.data.recipe.source.RecipeRemoteDataSource
import javax.inject.Inject

class RecipeRemoteDataSourceImpl @Inject constructor() : RecipeRemoteDataSource {

    override suspend fun getRecipes(size: Int, page: Int): Result<List<RecipeEntity>> {
      return Result.Success(Dummy().createListRecipe(size, page))
    }

    override suspend fun getRecipeDetails(recipeId: Int): Result<RecipeEntity> {
        return Result.Success(Dummy().getRecipeDetails(recipeId))
    }

}