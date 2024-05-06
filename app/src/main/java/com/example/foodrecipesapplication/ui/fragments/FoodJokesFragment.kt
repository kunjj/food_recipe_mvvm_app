package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodrecipesapplication.databinding.FragmentFoodJokesBinding

class FoodJokesFragment : Fragment() {
    private var binding: FragmentFoodJokesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        this.binding = FragmentFoodJokesBinding.inflate(inflater, container, false)
        return this.binding!!.root
    }
}