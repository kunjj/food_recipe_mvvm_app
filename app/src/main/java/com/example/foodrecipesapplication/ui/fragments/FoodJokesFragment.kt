package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.foodrecipesapplication.databinding.FragmentFoodJokesBinding
import com.example.foodrecipesapplication.network.NetworkResponse
import com.example.foodrecipesapplication.utils.Constant
import com.example.foodrecipesapplication.viewmodels.FoodRecipesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodJokesFragment : BaseFragment() {
    private var binding: FragmentFoodJokesBinding? = null
    private val foodRecipeViewModel by viewModels<FoodRecipesViewModel>()

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
                    it.data?.let { data -> this.binding!!.foodJoke = data }
                }

                is NetworkResponse.Error -> {
                    hideProgressbar()
                    loadDataFromCache()
                }
            }
        }
    }

    private fun loadDataFromCache() {
        this.foodRecipeViewModel.readRandomJoke.observe(viewLifecycleOwner) {
            it?.foodJoke?.let { this.binding!!.foodJoke = it }
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