package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipesapplication.adapters.FoodRecipeAdapter
import com.example.foodrecipesapplication.databinding.FragmentRecipeBinding
import com.example.foodrecipesapplication.network.NetworkResponse
import com.example.foodrecipesapplication.ui.MainActivity
import com.example.foodrecipesapplication.utils.Constant

class RecipeFragment : Fragment() {
    private lateinit var binding: FragmentRecipeBinding
    private val foodRecipesViewModel by lazy { (activity as MainActivity).foodRecipesViewModel }
    private val foodRecipeAdapter by lazy { FoodRecipeAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        this.binding = FragmentRecipeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        fetchDataFromApi()

        foodRecipesViewModel.foodRecipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Success -> {
                    stopShimmerEffect()
                    response.data!!.let { foodRecipeAdapter.recipes.submitList(it.recipes.toList()) }
                }

                is NetworkResponse.Error -> {
                    stopShimmerEffect()
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                }

                is NetworkResponse.Loading -> showShimmerEffect()

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

    private fun setUpRecyclerView() = binding.recyclerView.apply {
        adapter = this@RecipeFragment.foodRecipeAdapter
        layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        showShimmerEffect()
    }

    private fun showShimmerEffect() = binding.recyclerView.showShimmer()

    private fun stopShimmerEffect() = binding.recyclerView.hideShimmer()
}