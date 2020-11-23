package com.porto.core.data.recipe

import com.porto.commons.models.Result
import com.porto.core.data.recipe.source.RecipeRemoteDataSource
import com.porto.core.domain.recipe.models.Recipe
import com.porto.core.domain.recipe.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(private val remoteDataSource: RecipeRemoteDataSource)
    : RecipeRepository {

    override suspend fun getListRecipe(size: Int, page: Int): Result<List<Recipe>> {
        return when(val requestResult = remoteDataSource.getRecipes(size, page)) {
            is Result.Success -> {
                val res = requestResult.value.map {
                    it.toRecipe()
                }

                Result.Success(res)
            }
            is Result.Error -> Result.Error(requestResult.errorMessage)
        }
    }

    override suspend fun getDetailRecipe(recipeId: Int): Result<Recipe> {
        return when(val requestResult = remoteDataSource.getRecipeDetails(recipeId)) {
            is Result.Success -> Result.Success(requestResult.value.toRecipe())
            is Result.Error -> Result.Error(requestResult.errorMessage)
        }
    }
}