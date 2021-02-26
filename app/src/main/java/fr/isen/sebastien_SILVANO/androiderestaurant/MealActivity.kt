package fr.isen.sebastien_SILVANO.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoMainBinding
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoMealBinding

class MealActivity : AppCompatActivity(){

    //binding
    private lateinit var binding : LyoMealBinding

    //info
    private val info : CodeInfo = CodeInfo("Meal","MealActivity.kt")



    //init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info.setFunctionName("onCreate")



        //init binding instance
        binding = LyoMealBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //SET LAYOUT TEXTS

        //get meal name
        Message(info).log("Getting meal name...")
        val mealName = intent.getStringExtra("meal")
        Message(info).log("Got \"$mealName\".")

        //title
        val title = findViewById<TextView>(R.id.meal_title)
        title.text = mealName

        //content
        val content = findViewById<TextView>(R.id.meal_content_text)
        content.text = mealName



        //GET GOOGLE REQUEST (Volley)

        //create request
        val queue = Volley.newRequestQueue(this)

        //Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.POST,
            "http://test.api.catering.bluecodegames.com/menu",
            Response.Listener<String> { response ->

                //write content
                content.text = "Response is: ${response.substring(0, 500)}"
                Message(info).log("Response is: ${response.substring(0, 500)}")
            },
            Response.ErrorListener { _ ->
                content.text = "Unable to get request"
                Error(info).log(false, "Unable to get request from \"http://test.api.catering.bluecodegames.com/menu\".")
            }
        )

        //Add the request to the RequestQueue.
        queue.add(stringRequest)



        //debug
        Message(info).log("Entering in Meal : $mealName.")



        //binding
        setContentView(R.layout.lyo_meal)
    }
}