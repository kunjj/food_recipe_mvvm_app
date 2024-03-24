package com.example.foodrecipesapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foodrecipesapplication.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private var binding: ActivityDetailsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
    }
}