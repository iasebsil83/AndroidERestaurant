package fr.isen.sebastien_SILVANO.androiderestaurant.mealInfo

import com.google.gson.annotations.SerializedName
import java.io.Serializable



data class MealInfoDetails(
        @SerializedName("id")
        val id :Int,

        @SerializedName("name_fr")
        val name :String,

        @SerializedName("images")
        val picts :List<String>,

        @SerializedName("prices")
        val prices :List<Price>,

        @SerializedName("ingredients")
        val ingr :List<Ingredient>
) : Serializable