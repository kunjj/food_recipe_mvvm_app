package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.adapters.FoodRecipeAdapter
import com.example.foodrecipesapplication.databinding.FragmentRecipeBinding
import com.example.foodrecipesapplication.network.NetworkResponse
import com.example.foodrecipesapplication.ui.MainActivity
import com.example.foodrecipesapplication.utils.Constant
import com.example.foodrecipesapplication.utils.observeOnce
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class RecipeFragment : Fragment(), View.OnClickListener {
    private var binding: FragmentRecipeBinding? = null
    private val foodRecipesViewModel by lazy { (activity as MainActivity).foodRecipesViewModel }
    private val foodRecipeAdapter by lazy { FoodRecipeAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment.
        this.binding = FragmentRecipeBinding.inflate(inflater)
        this.binding!!.recipesViewModel = this.foodRecipesViewModel
        this.binding!!.lifecycleOwner = this
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        fetchDataFromDatabase()
        binding!!.btnFilterRecipe.setOnClickListener(this)

        foodRecipesViewModel.foodRecipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Success -> {
                    stopShimmerEffect()
                    response.data!!.let { foodRecipeAdapter.recipes.submitList(it.recipes.toList()) }
                }

                is NetworkResponse.Error -> {
                    stopShimmerEffect()
                    loadDataFromCache()
                    response.message?.let { Snackbar.make(view, it,Snackbar.LENGTH_SHORT).show() }
                }

                is NetworkResponse.Loading -> showShimmerEffect()
            }
        }
    }

    private fun setUpRecyclerView() = binding!!.recyclerView.apply {
        adapter = this@RecipeFragment.foodRecipeAdapter
        layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        showShimmerEffect()
    }

    private fun fetchDataFromDatabase() =
        lifecycleScope.launch {
            foodRecipesViewModel.readRecipes.observeOnce {
                if (it.isNotEmpty()) {
                    foodRecipeAdapter.recipes.submitList(it[0].foodRecipe.recipes.toList())
                    stopShimmerEffect()
                } else fetchDataFromApi()
            }
        }

    private fun loadDataFromCache() =
        lifecycleScope.launch {
            foodRecipesViewModel.readRecipes.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    foodRecipeAdapter.recipes.submitList(it[0].foodRecipe.recipes.toList())
                    stopShimmerEffect()
                }
            }
        }

    private fun fetchDataFromApi() = foodRecipesViewModel.getRandomRecipes(queries())

    private fun queries(): HashMap<String, String> {
        val queries = HashMap<String, String>()
        queries["apiKey"] = Constant.API_KEY
        queries["number"] = "50"
        return queries
    }

    private fun showShimmerEffect() = binding!!.recyclerView.showShimmer()

    private fun stopShimmerEffect() = binding!!.recyclerView.hideShimmer()
    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btnFilterRecipe -> {
                findNavController().navigate(R.id.action_recipeFragment_to_recipesFilterFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
    }
}