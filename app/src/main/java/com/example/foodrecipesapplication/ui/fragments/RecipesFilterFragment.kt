package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.foodrecipesapplication.databinding.FragmentRecipesFilterBinding
import com.example.foodrecipesapplication.ui.activities.RecipeActivity
import com.example.foodrecipesapplication.utils.Constant
import com.example.foodrecipesapplication.viewmodels.RecipeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class RecipesFilterFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentRecipesFilterBinding
    private val recipeViewModel: RecipeViewModel by lazy { (activity as RecipeActivity).recipeViewModel }
    private var mealType = Constant.DEFAULT_MEAL_TYPE
    private var mealTypeId = 0
    private var dietType = Constant.DEFAULT_DIET_TYPE
    private var dietTypeId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentRecipesFilterBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeViewModel.readMealAndType.asLiveData().observe(requireActivity()) { mealAndDietType ->
            this.mealType = mealAndDietType.mealType
            this.dietType = mealAndDietType.dietType
            this.mealTypeId = mealAndDietType.mealTypeId
            this.dietTypeId = mealAndDietType.dietTypeId
            updateChip(this.mealTypeId, binding.cgMealType)
            updateChip(this.dietTypeId, binding.cgDietType)
        }

        @Suppress("DEPRECATION") binding.cgMealType.setOnCheckedChangeListener { chipGroup, checkedChip ->
            val chip: Chip? = chipGroup.findViewById(checkedChip)
            this.mealTypeId = checkedChip
            this.mealType = chip?.text.toString().lowercase()
        }

        @Suppress("DEPRECATION") binding.cgDietType.setOnCheckedChangeListener { chipGroup, checkedChip ->
            val chip: Chip? = chipGroup.findViewById(checkedChip)
            this.dietTypeId = checkedChip
            this.dietType = chip?.text.toString().lowercase()
        }

        binding.btnApply.setOnClickListener {
            recipeViewModel.saveMealAndType(
                this.mealType, this.mealTypeId, this.dietType, this.dietTypeId
            )
            findNavController().navigate(
                RecipesFilterFragmentDirections.actionRecipesFilterFragmentToRecipeFragment(
                    true
                )
            )
        }
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        try {
            chipGroup.findViewById<Chip>(chipId).isChecked = true
        } catch (e: Exception) {
            Log.d("RecipeFilterFragment", e.message!!)
        }
    }
}