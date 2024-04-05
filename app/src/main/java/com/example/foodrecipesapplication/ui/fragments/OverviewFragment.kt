package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodrecipesapplication.adapters.RecipeDetailsAdapter
import com.example.foodrecipesapplication.databinding.FragmentOverviewBinding
import com.example.foodrecipesapplication.models.Recipe

class OverviewFragment : Fragment() {
    private var binding: FragmentOverviewBinding? = null
    private lateinit var recipe: Recipe
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentOverviewBinding.inflate(inflater, container, false)
        this.recipe = arguments?.getParcelable("recipe")!!
        this.binding!!.recipe = this.recipe
        return this.binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.rvRecipeDetails.apply {
            adapter = RecipeDetailsAdapter(requireContext(), recipe)
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
    }
}