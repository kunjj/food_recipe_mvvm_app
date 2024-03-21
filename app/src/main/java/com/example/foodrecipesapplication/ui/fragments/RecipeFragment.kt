package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.adapters.FoodRecipeAdapter
import com.example.foodrecipesapplication.databinding.FragmentRecipeBinding
import com.example.foodrecipesapplication.network.NetworkResponse
import com.example.foodrecipesapplication.ui.MainActivity
import com.example.foodrecipesapplication.utils.observeOnce
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@AndroidEntryPoint
class RecipeFragment : Fragment(), View.OnClickListener {
    private val args by navArgs<RecipeFragmentArgs>()
    private var binding: FragmentRecipeBinding? = null
    private val foodRecipesViewModel by lazy { (activity as MainActivity).foodRecipesViewModel }
    private val recipeViewModel by lazy { (activity as MainActivity).recipeViewModel }
    private val foodRecipeAdapter by lazy { FoodRecipeAdapter() }
    private val networkListener by lazy { (activity as MainActivity).networkListener }

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

        lifecycleScope.launch {
            networkListener.checkNetworkAvailability(requireContext()).collect { status ->
                Log.d("dcaacf", status.toString())
            }
        }

        foodRecipesViewModel.foodRecipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Success -> {
                    stopShimmerEffect()
                    response.data!!.let { foodRecipeAdapter.recipes.submitList(it.recipes.toList()) }
                }

                is NetworkResponse.Error -> {
                    stopShimmerEffect()
                    loadDataFromCache()
                    if (response.message != requireActivity().getString(R.string.not_connected_to_internet))
                        response.message?.let {
                            Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
                        }
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
            delay(750)
            foodRecipesViewModel.readRecipes.observeOnce {
                if (it.isNotEmpty() && !args.fromRecipeFilterFragment) {
                    foodRecipeAdapter.recipes.submitList(it[0].foodRecipe.recipes.toList())
                    stopShimmerEffect()
                } else fetchDataFromApi()
            }
        }

    private fun loadDataFromCache() =
        lifecycleScope.launch {
            delay(750)
            foodRecipesViewModel.readRecipes.observe(requireActivity()) {
                if (it.isNotEmpty()) {
                    foodRecipeAdapter.recipes.submitList(it[0].foodRecipe.recipes.toList())
                    stopShimmerEffect()
                }
            }
        }

    private fun fetchDataFromApi() =
        foodRecipesViewModel.getRandomRecipes(recipeViewModel.queries())

    private fun showShimmerEffect() = binding!!.recyclerView.showShimmer()

    private fun stopShimmerEffect() = binding!!.recyclerView.hideShimmer()
    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btnFilterRecipe -> {
                // There are 2 ways to navigate from one fragment to another.
                findNavController().navigate(RecipeFragmentDirections.actionRecipeFragmentToRecipesFilterFragment())
//                findNavController().navigate(R.id.action_recipeFragment_to_recipesFilterFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
    }
}