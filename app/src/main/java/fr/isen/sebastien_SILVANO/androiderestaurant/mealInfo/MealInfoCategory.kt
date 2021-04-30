package fr.isen.sebastien_SILVANO.androiderestaurant.mealInfo

import com.google.gson.annotations.SerializedName

data class MealInfoCategory(

        @SerializedName("name_fr")
        val name :String,

        @SerializedName("items")
        val detailList :List<MealInfoDetails>
)