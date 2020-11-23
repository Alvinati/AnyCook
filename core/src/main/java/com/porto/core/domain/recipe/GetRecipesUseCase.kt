package com.porto.core.domain.recipe

import com.porto.commons.base.UseCase
import com.porto.commons.models.Result
import com.porto.core.domain.recipe.models.Recipe
import com.porto.core.domain.recipe.repository.RecipeRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val recipeRepository: RecipeRepository)
    : UseCase<List<Recipe>, GetRecipesUseCase.Params>(){

    override suspend fun build(params: Params?): Result<List<Recipe>> {

        return when(val result = recipeRepository.getListRecipe(params?.size ?: 0, params?.page ?: 1)){
            is Result.Success -> {
                result
            }
            is Result.Error -> Result.Error("Failed to load recipes")
        }

    }

    class Params(val size: Int, val page: Int)
}