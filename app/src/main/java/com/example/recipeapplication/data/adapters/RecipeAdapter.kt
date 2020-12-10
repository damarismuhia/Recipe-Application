package com.example.recipeapplication.data.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapplication.R
import com.example.recipeapplication.data.models.Recipe
import com.example.recipeapplication.data.models.RecipeX
import com.example.recipeapplication.databinding.GridItemLayoutBinding
import com.example.recipeapplication.ui.fragments.RecipeClick
import kotlinx.android.synthetic.main.grid_item_layout.view.*


class RecipeAdapter(val callback: RecipeClick) :
    RecyclerView.Adapter<RecipeViewHolder>() {

    /**
     * The murder that our Adapter will show
     */
    var recipe: List<RecipeX> = emptyList()
        set(value) {
            field = value
            /**
             * TO DO() update data using paging library
             * */
            notifyDataSetChanged()
        }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val withDataBinding: GridItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            RecipeViewHolder.LAYOUT,
            parent,
            false
        )
        return RecipeViewHolder(withDataBinding)
    }


    override fun getItemCount() = recipe.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.recipe = recipe[position]
            it.recipeCallBack = callback
        }
    }
}

    class RecipeViewHolder(val viewDataBinding: GridItemLayoutBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.grid_item_layout
        }
    }
