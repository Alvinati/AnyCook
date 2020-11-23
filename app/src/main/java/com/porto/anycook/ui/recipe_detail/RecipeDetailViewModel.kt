package com.porto.anycook.ui.recipe_detail

import com.porto.anycook.ui.base.AnyCookViewModel
import com.porto.commons.models.Result
import com.porto.core.domain.common.module.ImageItem
import com.porto.core.domain.recipe.GetRecipeDetailUseCase
import com.porto.core.domain.recipe.models.Ingredients
import com.porto.core.domain.recipe.models.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(private val getRecipeDetailUseCase: GetRecipeDetailUseCase)
    : AnyCookViewModel<RecipeDetailViewModel.Event, RecipeDetailViewModel.State>() {

    private var recipeDetail : Recipe? = null

    sealed class Event {
        class LoadDetail(val recipeId: Int) : Event()
        object LoadIngredient : Event()
        object LoadSteps : Event()
    }

    sealed class State {
        class ShowIngredients(val ingredients: List<Ingredients>) : State()
        class ShowSteps(val steps: String) : State()
        class ShowHeaderDetails(val name: String,
                                val likeCountText: String,
                                val durationText: String,
                                val recipeImgUrls: List<ImageItem>?) : State()
        class ShowError(val message: String) : State()
    }

    override fun onEventReceived(event: Event) {
        when(event){
            is Event.LoadDetail -> loadDetail(event.recipeId)
            is Event.LoadIngredient -> loadIngredient()
            is Event.LoadSteps -> loadSteps()
        }
    }

    private fun loadDetail(recipeId: Int) = launch {
        val result = withContext(Dispatchers.IO){
            getRecipeDetailUseCase.execute(recipeId)
        }

        when(result){
            is Result.Success -> {
                recipeDetail = result.value
                pushState(State.ShowHeaderDetails(
                        result.value.name,
                        result.value.countLikeText,
                        result.value.serveMinutesText,
                        result.value.imageUrls))
            }

            is Result.Error -> pushState(State.ShowError(result.errorMessage ?: "Something went wrong"))
        }
    }

    private fun loadIngredient() {
        val ingredients = mutableListOf<Ingredients>()

        if(recipeDetail != null || recipeDetail?.ingredients != null)
            ingredients.addAll(recipeDetail!!.ingredients!!)

        pushState(State.ShowIngredients(ingredients))
    }

    private fun loadSteps() {
        val steps = recipeDetail?.cookingSteps ?: ""
        pushState(State.ShowSteps(steps))
    }



}