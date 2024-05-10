package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodrecipesapplication.databinding.FragmentFoodJokesBinding
import com.example.foodrecipesapplication.network.NetworkResponse
import com.example.foodrecipesapplication.ui.activities.RecipeActivity
import com.example.foodrecipesapplication.utils.Constant

class FoodJokesFragment : BaseFragment() {
    private var binding: FragmentFoodJokesBinding? = null
    private val foodRecipeViewModel by lazy { (activity as RecipeActivity).foodRecipesViewModel }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        this.binding = FragmentFoodJokesBinding.inflate(inflater, container, false)
        return this.binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.foodRecipeViewModel.getFoodJoke(Constant.API_KEY)

        this.foodRecipeViewModel.randomFoodJoke.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResponse.Loading -> showProgressbar()

                is NetworkResponse.Success -> {
                    hideProgressbar()
                    it.data?.let { data -> this.binding!!.tvFoodJoke.text = data.text }
                }

                is NetworkResponse.Error -> {
                    hideProgressbar()
                    loadDataFromCache()
                    showSnackBar(requireView(), it.message.toString())
                }
            }
        }
    }

    private fun loadDataFromCache() {
        this.foodRecipeViewModel.readRandomJoke.observe(viewLifecycleOwner) {
            it.foodJoke.text.let { data -> this.binding!!.tvFoodJoke.text = data }
        }
    }

    private fun showProgressbar() {
        this.binding!!.progressBar.visibility = View.VISIBLE
        this.binding!!.tvFoodJoke.visibility = View.GONE
    }

    private fun hideProgressbar() {
        this.binding!!.progressBar.visibility = View.GONE
        this.binding!!.tvFoodJoke.visibility = View.VISIBLE
    }
}