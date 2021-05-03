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
import fr.isen.sebastien_SILVANO.androiderestaurant.mealInfo.MealInfoDetails



class MealActivity : AppCompatActivity(){



    //binding
    private lateinit var binding : LyoMealBinding

    //debug info
    private val info : CodeInfo = CodeInfo("Meal", "MealActivity.kt")

    //meal
    private lateinit var meal : MealInfoDetails

    //cart
    private var cartCount = 0
    private var totalPrice = 0



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
                if(meal.picts[0].isNullOrEmpty()){
                    Picasso.get().load("@drawable/no_picture").into(imageView)
                }else {
                    Picasso.get().load(meal.picts[position]).into(imageView)
                }
            }
        carouselView.pageCount = sampleImages.size;
        carouselView.setImageListener(imageListener);



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
        totalPrice = cartCount * meal.prices[0].value.toInt()
        findViewById<TextView>(R.id.meal_price_total).text = "Total : ${totalPrice}€"
    }
}
