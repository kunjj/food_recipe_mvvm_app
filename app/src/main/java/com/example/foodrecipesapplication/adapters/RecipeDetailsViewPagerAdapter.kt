package com.example.foodrecipesapplication.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class RecipeDetailsViewPagerAdapter(
    private val bundle: Bundle,
    private val fragments: List<Fragment>,
    private val titles: List<String>,
    manager: FragmentManager,
) : FragmentPagerAdapter(manager) {

    override fun getCount(): Int = this.fragments.size

    override fun getItem(position: Int): Fragment {
        this.fragments[position].arguments = this.bundle
        return this.fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence = this.titles[position]
}