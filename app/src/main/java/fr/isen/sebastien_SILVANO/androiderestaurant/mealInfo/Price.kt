package fr.isen.sebastien_SILVANO.androiderestaurant.mealInfo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Price(
        @SerializedName("id")
        val id :Int,

        @SerializedName("price")
        val value :String,

        @SerializedName("size")
        val size :String
) : Serializable