package com.porto.core.domain.recipe

import com.porto.commons.base.UseCase
import com.porto.commons.models.Result
import com.porto.core.domain.recipe.models.Recipe
import com.porto.core.domain.recipe.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(private val repository: RecipeRepository)
    : UseCase<Recipe, Int>() {


    override suspend fun build(params: Int?): Result<Recipe> {

        if(params == null)
            return Result.Error("Recipe not found")

        return repository.getDetailRecipe(params)
    }


}