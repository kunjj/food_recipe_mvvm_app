package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipesapplication.adapters.FavoriteRecipeAdapter
import com.example.foodrecipesapplication.databinding.FragmentFavoriteRecipeBinding

class FavoriteRecipeFragment : Fragment() {
    private var binding: FragmentFavoriteRecipeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentFavoriteRecipeBinding.inflate(inflater)
        return this.binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding!!.rvFavoriteRecipes.apply {
            adapter = FavoriteRecipeAdapter()
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
    }
}