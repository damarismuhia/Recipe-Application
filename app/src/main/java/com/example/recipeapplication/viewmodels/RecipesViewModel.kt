package com.example.recipeapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeapplication.data.adapters.RecipeAdapter
import com.example.recipeapplication.data.models.Recipe
import com.example.recipeapplication.data.models.RecipeX
import com.example.recipeapplication.data.network.RetrofitBuilder
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class RecipesViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    /**
     *  LiveData gives us updated data when they change.
     * */
    private var allRecipes: MutableLiveData<Recipe> = MutableLiveData()

    private var adapter: RecipeAdapter

    init {
        fetchMurdersInCoroutine()
        adapter = RecipeAdapter()
    }

    fun getMyAdapter(): RecipeAdapter {
        return adapter
    }

    fun setAdapterData(data: List<RecipeX>) {
        adapter.apply {
            setDataList(data)
            notifyDataSetChanged()
        }
    }

    fun getAllRecipesData(): MutableLiveData<Recipe> {
        return allRecipes
    }

    private fun fetchMurdersInCoroutine() = viewModelScope.launch {
        try {
            val fetchingMurders = RetrofitBuilder.apiService.getAllRecipes().await()
            allRecipes.postValue(fetchingMurders)
        } catch (networkError: IOException) {
            allRecipes.postValue(null)
        }
    }
}