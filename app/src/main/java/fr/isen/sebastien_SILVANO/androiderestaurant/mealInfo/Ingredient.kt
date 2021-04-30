package fr.isen.sebastien_SILVANO.androiderestaurant.mealInfo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Ingredient(
        @SerializedName("id")
        val id :Int,

        @SerializedName("name_fr")
        val name :String
) : Serializable