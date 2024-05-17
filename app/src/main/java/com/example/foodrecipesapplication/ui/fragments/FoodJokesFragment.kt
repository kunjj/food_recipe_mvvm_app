package com.example.foodrecipesapplication.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.databinding.FragmentFoodJokesBinding
import com.example.foodrecipesapplication.network.NetworkResponse
import com.example.foodrecipesapplication.ui.activities.RecipeActivity
import com.example.foodrecipesapplication.utils.Constant
import com.example.foodrecipesapplication.viewmodels.FoodRecipesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodJokesFragment : BaseFragment() {
    private var binding: FragmentFoodJokesBinding? = null
    private val foodRecipeViewModel by viewModels<FoodRecipesViewModel>()
    private lateinit var menuHost: MenuHost
    var foodJoke = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        this.binding = FragmentFoodJokesBinding.inflate(inflater, container, false)
        this.menuHost = requireActivity()
        (activity as RecipeActivity).setSupportActionBar(binding!!.toolbar)
        (activity as RecipeActivity).supportActionBar?.title =
            context?.getString(R.string.food_joke)
        return this.binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.foodRecipeViewModel.getFoodJoke(Constant.API_KEY)
        setMenuBar()
        this.foodRecipeViewModel.randomFoodJoke.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResponse.Loading -> showProgressbar()

                is NetworkResponse.Success -> {
                    hideProgressbar()
                    it.data?.let { data ->
                        this.binding!!.foodJoke = data
                        this.foodJoke = data.text
                    }
                }

                is NetworkResponse.Error -> {
                    hideProgressbar()
                    loadDataFromCache()
                }
            }
        }
    }

    private fun setMenuBar() {
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) =
                menuInflater.inflate(R.menu.food_joke_menu, menu)

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.shareFoodJoke -> {
                        val shareIntent = Intent().apply {
                            this.action = Intent.ACTION_SEND
                            this.putExtra(Intent.EXTRA_TEXT, this@FoodJokesFragment.foodJoke)
                            this.type = "text/plain"
                        }
                        startActivity(Intent.createChooser(shareIntent, "Share via"))
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun loadDataFromCache() {
        this.foodRecipeViewModel.readRandomJoke.observe(viewLifecycleOwner) {
            it?.foodJoke?.let { foodJoke ->
                this.binding!!.foodJoke = foodJoke
                Log.d("sdvv", foodJoke.text)
                this.foodJoke = foodJoke.text
            }
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

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
    }
}