package com.example.recipeapplication.data.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapplication.R
import com.example.recipeapplication.data.models.Recipe
import com.example.recipeapplication.data.models.RecipeX
import kotlinx.android.synthetic.main.grid_item_layout.view.*


class RecipeAdapter :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    private var items = Recipe(0, ArrayList())

    fun setDataList(items: List<RecipeX>) {
        this.items.recipes = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecipeViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.grid_item_layout,
            parent,
            false
        )
    )


    override fun getItemCount() = items.recipes.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        val recipes = items.recipes[position]

        holder.view.apply {
            title.text = recipes.title
        }

        val url = recipes.image_url

        Glide
            .with(holder.view.context)
            .load(url)
            .centerCrop()
            .into(holder.view.imageUrl)

        holder.view.setOnClickListener {
            Toast.makeText(holder.view.context, recipes.title, Toast.LENGTH_SHORT).show()
        }
    }

    class RecipeViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}