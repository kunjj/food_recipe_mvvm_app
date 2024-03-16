package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodrecipesapplication.databinding.FragmentRecipesFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RecipesFilterFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentRecipesFilterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        this.binding = FragmentRecipesFilterBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}