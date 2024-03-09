package com.example.foodrecipesapplication.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foodrecipesapplication.R

class RecipeBindingAdapter {
    companion object {
        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, url: String) {
            imageView.load(url) {
                crossfade(1000)
            }
        }

        @BindingAdapter("numberOfLikes")
        @JvmStatic
        fun setNumbersOfLikes(textView: TextView, likes: Int) {
            textView.text = likes.toString()
        }

        @BindingAdapter("timeToCook")
        @JvmStatic
        fun setTimeToCook(textView: TextView, time: Int) {
            textView.text = time.toString()
        }

        @BindingAdapter("isVegan")
        @JvmStatic
        fun isVegan(view: View, isVegan: Boolean) {
            if (isVegan) {
                when (view) {
                    is TextView -> view.setTextColor(
                        ContextCompat.getColor(
                            view.context, R.color.green
                        )
                    )

                    is ImageView -> view.setColorFilter(
                        ContextCompat.getColor(
                            view.context, R.color.green
                        )
                    )
                }
            }
        }
    }
}