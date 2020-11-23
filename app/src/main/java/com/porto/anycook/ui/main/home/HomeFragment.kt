package com.porto.anycook.ui.main.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.porto.anycook.ui.base.AnyCookFragment
import com.porto.anycook.R
import com.porto.anycook.ui.commons.VerticalDivider
import com.porto.anycook.ui.main.MainFragmentDirections
import com.porto.anycook.ui.main.home.adapter.RecipeItemListener
import com.porto.anycook.ui.main.home.adapter.RecipeItemsAdapter
import com.porto.core.domain.recipe.models.Recipe
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : AnyCookFragment() {

    private val homeViewModel by activityViewModels<HomeViewModel> { viewModelFactory }
    private lateinit var recipeItemsAdapter: RecipeItemsAdapter

    override val layoutResourceId: Int = R.layout.home_fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.onEventReceived(HomeViewModel.Event.OnLoadData)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModels()
        setupRecipeRecyclerView()
    }

    private fun setupRecipeRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        val verticalDivider = VerticalDivider(requireContext(), 10)
        rv_recipe.layoutManager = layoutManager
        rv_recipe.addItemDecoration(verticalDivider)
        recipeItemsAdapter = RecipeItemsAdapter(Glide.with(this), RecipeItemListener {
            homeViewModel.onEventReceived(HomeViewModel.Event.ClickRecipeItem(it))
        })
        rv_recipe.adapter = recipeItemsAdapter
    }


    private fun bindViewModels() {
        homeViewModel.state.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled().let { state ->
                when(state) {
                    is HomeViewModel.State.UserVisible -> toggleUser(true)
                    is HomeViewModel.State.UserInvisible -> toggleUser(false)
                    is HomeViewModel.State.ShowUser -> showUser(state.name, state.avatarUrl)
                    is HomeViewModel.State.ShowError -> showToast(state.message)
                    is HomeViewModel.State.ShowLoadedRecipes -> updateRecipeListItems(state.items)
                    is HomeViewModel.State.GoToAuthorizationPage -> fragmentInteractionListener
                            .navigateTo(MainFragmentDirections.actionMainFragmentToLoginFragment())
                    is HomeViewModel.State.OpenRecipeDetail -> fragmentInteractionListener
                            .navigateTo(MainFragmentDirections
                                    .actionMainFragmentToRecipeDetailFragment(state.recipeId))
                }
            }

        })
    }

    private fun toggleUser(isVisible : Boolean) {
        val visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        tv_username.visibility = visibility
        img_avatar.visibility = visibility
    }

    private fun showUser(name: String, ava: String?) {
        tv_username.text = name
        Glide.with(this)
            .load(ava)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder_account)
            .circleCrop()
            .into(img_avatar)
    }

    private fun updateRecipeListItems(item: List<Recipe>) {
        recipeItemsAdapter.submitList(item)
        recipeItemsAdapter.notifyDataSetChanged()
    }

}