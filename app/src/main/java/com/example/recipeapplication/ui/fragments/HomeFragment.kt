package com.example.recipeapplication.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapplication.R
import com.example.recipeapplication.data.adapters.RecipeAdapter
import com.example.recipeapplication.data.models.RecipeX
import com.example.recipeapplication.databinding.FragmentHomeBinding
import com.example.recipeapplication.viewmodels.RecipesViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val viewModel: RecipesViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, RecipesViewModel.Factory(activity.application))
            .get(RecipesViewModel::class.java)
    }

    private var viewModelAdapter: RecipeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allRecipes.observe(viewLifecycleOwner, { recipe ->
            recipe?.apply {
                viewModelAdapter?.recipe = recipes
            }
        })
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner

        binding.fragmentRecipeViewModel = viewModel
        
        binding.numberOfRecipes.text = "Number of recipes : ${viewModel.allRecipes.value?.count}"

        viewModelAdapter = RecipeAdapter(RecipeClick {
            Toast.makeText(requireContext(), it.title, Toast.LENGTH_SHORT).show()
            /**
             * Get single item selected values
             * */
            val imageUrl: String = it.image_url
            val title:String = it.title
            val publisher:String = it.publisher
            val sourceUrl:String = it.source_url

            /**
             * Navigate with arguments
             * */

        })

        binding.root.findViewById<RecyclerView>(R.id.recyclerView).apply {
            val gridLayoutManager =
                GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
            layoutManager = gridLayoutManager
            adapter = viewModelAdapter
        }

        return binding.root
    }


}

class RecipeClick(val block: (RecipeX) -> Unit) {

    fun onClick(recipe: RecipeX) = block(recipe)
}