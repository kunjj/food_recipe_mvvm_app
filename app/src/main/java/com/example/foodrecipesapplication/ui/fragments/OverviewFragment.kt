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
    private lateinit var recipeDetailsAdapter: RecipeDetailsAdapter
    private lateinit var recipe: Recipe
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        this.binding = FragmentOverviewBinding.inflate(inflater)

        this.recipe = requireArguments().getParcelable("recipe", Recipe::class.java)!!

        return this.binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeDetailsAdapter = RecipeDetailsAdapter(requireContext(), recipe)
        binding!!.rvRecipeDetails.apply {
            adapter = recipeDetailsAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
    }
}