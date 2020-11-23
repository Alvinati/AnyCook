package com.porto.anycook.ui.main.home

import com.porto.anycook.ui.base.AnyCookViewModel
import com.porto.commons.models.Result
import com.porto.core.domain.recipe.GetRecipesUseCase
import com.porto.core.domain.recipe.models.Recipe
import com.porto.core.domain.user.GetUserUseCase
import com.porto.core.domain.user.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getUserUseCase: GetUserUseCase)

    : AnyCookViewModel<HomeViewModel.Event, HomeViewModel.State>() {

    private val items = mutableListOf<Recipe>()
    private  var user : User? = null

    sealed class Event {
        object OnLoadData : Event()
        class NeedLoadRecipe(val size: Int, val page: Int) : Event()
        class ClickRecipeItem(val recipeId: Int) : Event()
    }

    sealed class State {
        class ShowLoadedRecipes(val items : List<Recipe>) : State()
        class ShowError(val message: String) : State()
        class ShowUser(val name: String, val avatarUrl: String?) : State()
        class OpenRecipeDetail(val recipeId: Int) : State()
        object UserVisible : State()
        object UserInvisible : State()
        object GoToAuthorizationPage : State()
    }

    override fun onEventReceived(event: Event) {
        when(event){
            is Event.OnLoadData -> initializeHome()
            is Event.NeedLoadRecipe -> loadRecipes(event.size, event.page)
            is Event.ClickRecipeItem -> pushState(State.OpenRecipeDetail(event.recipeId))
        }
    }

    private fun loadRecipes(size: Int, page: Int) = launch {
        val result = withContext(Dispatchers.IO){
            getRecipesUseCase.execute(GetRecipesUseCase.Params(size, page))
        }

        when(result) {
            is Result.Success -> State.ShowLoadedRecipes(result.value)
            is Result.Error -> State.ShowError(result.errorMessage ?: "Terjadi Kesalahan")
        }
    }

    private fun initializeHome() = launch {
        val result = withContext(Dispatchers.IO){
           getUserUseCase.execute()
        }

        when(result) {
            is Result.Success -> {

                user = result.value
                pushState(State.UserVisible)
                pushState(State.ShowUser(result.value.name, result.value.avatarUrl))

                val recipes = withContext(Dispatchers.IO){
                    getRecipesUseCase.execute(GetRecipesUseCase.Params(6, 1))
                }

                when(recipes) {
                    is Result.Success -> {
                        items.clear()
                        items.addAll(recipes.value)
                        pushState(State.ShowLoadedRecipes(recipes.value))
                    }
                    else -> {
                        pushState(State.ShowError("Failed to load recipes"))
                    }
                }
            }
            is Result.Error ->  {
                pushState(State.ShowError(result.errorMessage ?: "Terjadi Kesalahan"))
                pushState(State.GoToAuthorizationPage)
            }
        }
    }


}