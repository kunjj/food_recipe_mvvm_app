package com.example.foodrecipesapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodrecipesapplication.R
import com.google.android.material.snackbar.Snackbar

open class BaseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    fun showSnackBar(view: View,message: String) = apply {
        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show()
    }
}