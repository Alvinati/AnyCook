package com.porto.anycook.ui.recipe_detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.porto.anycook.ui.base.AnyCookFragment
import com.porto.anycook.R
import com.porto.anycook.ui.commons.PageAdapter
import com.porto.anycook.ui.commons.VerticalDivider
import com.porto.anycook.ui.main.home.HomeViewModel
import com.porto.anycook.ui.main.home.adapter.RecipeItemListener
import com.porto.anycook.ui.main.home.adapter.RecipeItemsAdapter
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.recipe_detail_fragment.*
import kotlinx.android.synthetic.main.recipe_detail_header.*

class RecipeDetailFragment  : AnyCookFragment() {

    override val layoutResourceId: Int = R.layout.recipe_detail_fragment

    private val tabTitles = arrayOf("Ingredients", "Cooking Steps")

    private val recipeDetailViewModel by activityViewModels<RecipeDetailViewModel>{viewModelFactory}

    private val args : RecipeDetailFragmentArgs by navArgs()

    private lateinit var imageAdapter: ImageAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        recipeDetailViewModel.onEventReceived(RecipeDetailViewModel.Event
                .LoadDetail(args.recipeId))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViewPager()
        bindingView()
        setupRecyclerViewImageHeader()
    }

    private fun setupRecyclerViewImageHeader() {
        val layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false)
        background.layoutManager = layoutManager
        imageAdapter = ImageAdapter(Glide.with(this))
        background.adapter = imageAdapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(background)
    }


    private fun bindingView() {
        recipeDetailViewModel.state.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled().let {
                state ->
                when(state) {
                    is RecipeDetailViewModel.State.ShowHeaderDetails -> {
                        btn_bottom_left.text = state.likeCountText
                        btn_bottom_right.text = state.durationText
                        imageAdapter.submitList(state.recipeImgUrls)
                        imageAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    private fun setUpViewPager() {

        val vpAdapter = PageAdapter(this)

        vpAdapter.addFragment(IngredientsFragment::class.qualifiedName!!)
        vpAdapter.addFragment(CookingStepsFragment::class.qualifiedName!!)
        vp_recipe_detail.adapter = vpAdapter

        TabLayoutMediator(tabLayout, vp_recipe_detail) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

}