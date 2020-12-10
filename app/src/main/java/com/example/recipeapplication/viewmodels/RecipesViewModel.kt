package com.example.recipeapplication.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.recipeapplication.data.models.Recipe
import com.example.recipeapplication.data.network.RetrofitBuilder
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    private val _allRecipes = MutableLiveData<Recipe>()

    val allRecipes: LiveData<Recipe>
        get() = _allRecipes


    private fun refreshDataFromNetwork() {
        viewModelScope.launch {
            try {
                val fetchingRecipes = RetrofitBuilder.apiService.getAllRecipes().await()
                _allRecipes.postValue(fetchingRecipes)
            } catch (networkError: IOException) {
                _allRecipes.postValue(null)
            }
        }
    }

    init {
        refreshDataFromNetwork()
    }


    /**
     * Factory for constructing RecipesViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RecipesViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}