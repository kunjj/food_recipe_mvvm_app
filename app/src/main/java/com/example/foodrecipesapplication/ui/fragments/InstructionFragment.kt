package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.foodrecipesapplication.databinding.FragmentInstructionBinding
import com.example.foodrecipesapplication.models.Recipe

class InstructionFragment : Fragment() {
    private var binding: FragmentInstructionBinding? = null
    private lateinit var recipe: Recipe
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        this.binding = FragmentInstructionBinding.inflate(inflater)
        return this.binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.recipe = arguments?.getParcelable("recipe")!!
        this.binding!!.wvRecipeInstruction.apply {
            webViewClient = WebViewClient()
            loadUrl(recipe.sourceUrl)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
    }
}