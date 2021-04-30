package fr.isen.sebastien_SILVANO.androiderestaurant.mealInfo

import com.google.gson.annotations.SerializedName

data class MealInfo(
        @SerializedName("data")
        val data :List<MealInfoCategory> = listOf()
)