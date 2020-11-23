package com.porto.anycook.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.porto.anycook.R
import com.porto.core.domain.recipe.models.Recipe
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeItemsAdapter(private val request: RequestManager
                         , private val clickListener: RecipeItemListener)
    : ListAdapter<Recipe, RecipeItemsAdapter.ViewHolder>(AsyncDifferConfig.Builder<Recipe>(
    object : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
            oldItem.name == newItem.name && oldItem.id == newItem.id
    }).build()
) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.tv_recipe_name.text = item.name
        holder.itemView.tv_duration.text = item.serveMinutesText
        request.load(item.thumbnailUrl).into(holder.itemView.img_recipe)
        holder.itemView.setOnClickListener {
            clickListener.onClick(item)
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        request.clear(holder.itemView.img_recipe)
    }

}

class RecipeItemListener(val clickListener : (recipeId : Int) -> Unit) {
    fun onClick(recipe: Recipe) = clickListener(recipe.id)
}
