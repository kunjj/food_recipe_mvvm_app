package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodrecipesapplication.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {
    private var binding: FragmentOverviewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        this.binding = FragmentOverviewBinding.inflate(inflater)
        return this.binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
    }
}