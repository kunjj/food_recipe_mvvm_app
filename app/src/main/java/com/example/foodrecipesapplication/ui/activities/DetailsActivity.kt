package com.example.foodrecipesapplication.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.example.foodrecipesapplication.R
import com.example.foodrecipesapplication.adapters.RecipeDetailsViewPagerAdapter
import com.example.foodrecipesapplication.databinding.ActivityDetailsBinding
import com.example.foodrecipesapplication.ui.fragments.IngredientsFragment
import com.example.foodrecipesapplication.ui.fragments.InstructionFragment
import com.example.foodrecipesapplication.ui.fragments.OverviewFragment

class DetailsActivity : AppCompatActivity() {
    private var binding: ActivityDetailsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setSupportActionBar(binding!!.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        this.binding!!.viewPager.adapter = getRecipeDetailsViewPagerAdapter()
        this.binding!!.tablayout.setupWithViewPager(this.binding!!.viewPager)
    }

    private fun getRecipeDetailsViewPagerAdapter(): RecipeDetailsViewPagerAdapter {
        val fragments = listOf(OverviewFragment(), IngredientsFragment(), InstructionFragment())

        val title = listOf(
            getString(R.string.overview),
            getString(R.string.ingredients),
            getString(R.string.instruction)
        )

        val args by navArgs<DetailsActivityArgs>()
        val bundle = Bundle().also {
            it.putParcelable("recipe",args.recipe)
        }

        return RecipeDetailsViewPagerAdapter(bundle,fragments,title,supportFragmentManager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // This is the Up button
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
    }
}