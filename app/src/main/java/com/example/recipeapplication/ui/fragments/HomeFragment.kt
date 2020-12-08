package com.example.recipeapplication.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapplication.R
import com.example.recipeapplication.databinding.FragmentHomeBinding
import com.example.recipeapplication.viewmodels.RecipesViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: RecipesViewModel
    lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind(view)!!

        viewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)
        binding.apply {
            fragmentRecipeViewModel = viewModel
            lifecycleOwner = this@HomeFragment
        }
        makeApiCallCoroutines()
    }

    @SuppressLint("SetTextI18n")
    private fun makeApiCallCoroutines(): RecipesViewModel {
        viewModel.getAllRecipesData().observe(viewLifecycleOwner, {
            if (it != null) {
                /**
                 * update the adapter
                 * */
                binding.numberOfRecipes.text = "Number of chicken recipes : ${it.count}"

                val gridLayoutManager =
                    GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
                binding.recyclerView.apply {
                    hasFixedSize()
                    layoutManager = gridLayoutManager
                }
                viewModel.setAdapterData(it.recipes)

            } else {
                Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        return viewModel
    }

}