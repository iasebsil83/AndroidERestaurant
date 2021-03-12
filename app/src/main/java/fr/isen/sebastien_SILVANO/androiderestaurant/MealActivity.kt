package fr.isen.sebastien_SILVANO.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoMealBinding
import android.widget.TextView
import org.json.JSONObject

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



        //SET TITLE TEXT

        //get meal name
        Message(info).log("Getting meal name...")
        val mealName = intent.getStringExtra("meal")
        findViewById<TextView>(R.id.meal_title).text = mealName
        Message(info).log("Got \"$mealName\".")



        //GET GOOGLE REQUEST (Volley)

        //create request
        val queue = Volley.newRequestQueue(this)

        //prepare request
        val jsonRequest = JSONObject()
        jsonRequest.put("id_shop", 1)

        //Request a string response from the provided URL.
        var content_text = ""
        val stringRequest = JsonObjectRequest(
            Request.Method.POST,
            "http://test.api.catering.bluecodegames.com/menu",
            jsonRequest,
            Response.Listener { response ->

                if(response == null){
                    Error(info).log(false, "Got null response.")
                }else {
                    Message(info).log("Response is: ${response.toString()}")
                    content_text = "Response is : ${response.toString()}"
                }
            },
            Response.ErrorListener { _ ->

                //error case
                content_text = "Unable to get request"
                Error(info).log(false, "Unable to get request from \"http://test.api.catering.bluecodegames.com/menu\".")
            }
        )

        //set content text
        findViewById<TextView>(R.id.meal_content_text).text = content_text

        //Add the request to the RequestQueue.
        queue.add(stringRequest)



        //debug
        Message(info).log("Entering in Meal : $mealName.")
    }
}