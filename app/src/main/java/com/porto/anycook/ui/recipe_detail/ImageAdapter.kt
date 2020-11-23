package com.porto.anycook.ui.recipe_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.porto.anycook.R
import com.porto.anycook.ui.main.home.adapter.RecipeItemsAdapter
import com.porto.core.domain.common.module.ImageItem
import kotlinx.android.synthetic.main.item_recipe.view.*

class ImageAdapter(private val requestManager: RequestManager)
    : ListAdapter<ImageItem, RecipeItemsAdapter.ViewHolder>(AsyncDifferConfig.Builder<ImageItem>(
        object : DiffUtil.ItemCallback<ImageItem>() {
            override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
                    oldItem.url == newItem.url && oldItem.id == newItem.id
        }).build()
) {

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemsAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_detail_item_image_background,
                parent, false)
        return RecipeItemsAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeItemsAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        requestManager.load(item.url).into(holder.itemView.img_recipe)
    }
}