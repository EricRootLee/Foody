package com.skylabstechke.foody.bindingadapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.skylabstechke.foody.data.room.RecipesEntity
import com.skylabstechke.foody.models.FoodRecipe
import com.skylabstechke.foody.models.Result
import com.skylabstechke.foody.ui.fragments.recipes.RecipesFragmentDirections
import com.skylabstechke.foody.utils.NetworkResult

class RecipesBindingAdapter {
    companion object {

        @BindingAdapter("onRecipeClickListener")
        @JvmStatic
        fun onRecipeClickListener(recipeRowLayout: ConstraintLayout, result: Result) {

            recipeRowLayout.setOnClickListener {
                try {

                    val action =
                        RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity2(result)
                    recipeRowLayout.findNavController().navigate(action)

                } catch (e: Exception) {
                    Log.d("onRecipeClickListener", e.toString())
                }
            }

        }


        @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
        @JvmStatic
        fun errImageViewVisibility(
            imageView: ImageView,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {

            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
            } else if (apiResponse is NetworkResult.Loading) {
                imageView.visibility = View.INVISIBLE
            } else if (apiResponse is NetworkResult.Success) {
                imageView.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("readApiResponse2", "readDatabase2", requireAll = true)
        @JvmStatic
        fun errTextViewVisibility(
            textView: TextView,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?

        ) {

            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                textView.visibility = View.VISIBLE
                textView.text = apiResponse.message.toString()
            } else if (apiResponse is NetworkResult.Loading) {
                textView.visibility = View.INVISIBLE
            } else if (apiResponse is NetworkResult.Success) {
                textView.visibility = View.INVISIBLE
            }


        }
    }
}