package com.example.recipeapplication.data.models

data class Recipe(val count: Int, var recipes: List<RecipeX>)

data class RecipeX(
    val _id: String,
    val image_url: String,
    val publisher: String,
    val publisher_url: String,
    val recipe_id: String,
    val social_rank: Double,
    val source_url: String,
    val title: String
)