package fr.isen.sebastien_SILVANO.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoMealBinding
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import fr.isen.sebastien_SILVANO.androiderestaurant.log.CodeInfo
import fr.isen.sebastien_SILVANO.androiderestaurant.log.Message
import fr.isen.sebastien_SILVANO.androiderestaurant.mealInfo.MealInfoDetails
import java.lang.Exception


class MealActivity : AppCompatActivity(){



    //binding
    private lateinit var binding : LyoMealBinding

    //debug info
    private val info : CodeInfo = CodeInfo("Meal", "MealActivity.kt")

    //meal
    private lateinit var meal : MealInfoDetails

    //cart
    private var cartCount = 0
    private var unitPrice : Float? = null
    private var totalPrice : Float = 0F



    //init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info.setFunctionName("onCreate")



        //LAYOUT

        //init binding instance
        binding = LyoMealBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //DISPLAY

        //get meal info
        meal = intent.extras?.get("meal") as MealInfoDetails
        unitPrice = meal.prices[0].value.toFloatOrNull()

        //set texts
        findViewById<TextView>(R.id.meal_title).text = meal.name
        findViewById<TextView>(R.id.meal_content_text).text = meal.ingr.map{ it.name }.joinToString()
        findViewById<TextView>(R.id.meal_price_per_unit).text = meal.prices[0].value + "€/unit"
        updateCartCount()

        //Carousel
        var sampleImages = arrayOf(meal.picts[0])
        val carouselView = findViewById<CarouselView>(R.id.meal_pict);
        var imageListener: ImageListener =
            ImageListener { position, imageView ->
                if(
                    !meal.picts.isNullOrEmpty() &&
                    !meal.picts[position].isNullOrEmpty()
                ){
                    Picasso.get().load( meal.picts[position] ).into(imageView)
                }
            }
        carouselView.pageCount = sampleImages.size;
        carouselView.setImageListener(imageListener)



        //CART COUNT

        //add
        binding.addToCart.setOnClickListener{
            cartCount++
            updateCartCount()
        }

        //remove
        binding.removeFromCart.setOnClickListener{
            if(cartCount > 0){
                cartCount--
            }
            updateCartCount()
        }
    }

    private fun updateCartCount(){
        if(unitPrice != null) {
            totalPrice = cartCount * unitPrice!!
        }
        findViewById<TextView>(R.id.meal_price_total).text = "Total : ${totalPrice}€"
    }
}
