package com.example.recipeapplication.data.network

import com.example.recipeapplication.data.models.Recipe
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("api/search?q=chicken")
    fun getAllRecipes(): Call<Recipe>
}