package com.example.recipeapplication.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.recipeapplication.R
import com.example.recipeapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    lateinit var binding: FragmentDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind(view)!!

        arguments?.let{
            val safeArgs = DetailFragmentArgs.fromBundle(it)

            val imageUrl = safeArgs.imageUrl
            val title = safeArgs.title
            val publisher = safeArgs.publisher
            val sourceUrl = safeArgs.sourceUrl

            val viewImage = binding.image
            val viewTitle = binding.title
            val viewPublisher = binding.publisher
            val viewSourceUrl = binding.sourceUrl


            Glide.with(requireContext()).load(imageUrl).error(R.drawable.ic_launcher_background).into(viewImage)

            viewTitle.text = title
            viewPublisher.text = "Publisher: $publisher"
            viewSourceUrl.text = "Source Url: $sourceUrl"
        }
    }

}