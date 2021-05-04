package fr.isen.sebastien_SILVANO.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoMealListBinding
import fr.isen.sebastien_SILVANO.androiderestaurant.log.CodeInfo
import fr.isen.sebastien_SILVANO.androiderestaurant.log.Error
import fr.isen.sebastien_SILVANO.androiderestaurant.log.Message
import fr.isen.sebastien_SILVANO.androiderestaurant.mealInfo.*
import org.json.JSONObject
import java.io.Serializable






class MealListActivity : AppCompatActivity(){






    //debug info
    private val info :CodeInfo = CodeInfo("MealList", "MealListActivity.kt")
    private val msg  : Message  = Message(info)
    private val err  : Error    = Error  (info)

    //binding
    private lateinit var binding :LyoMealListBinding

    //category selection
    private var categoryNbr: Int = CATEGORY_ENTREE

    //meals
    private var meals :MealInfo = MealInfo(
        listOf(
            MealInfoCategory(
                "UndefinedCategory",
                listOf(
                    MealInfoDetails(
                        -1,
                        "UndefinedMeal",
                        listOf("UndefinedPicture"),
                        listOf( Price     (-1, "NoPrice", "UndefinedSize") ),
                        listOf( Ingredient(-1, "UndefinedIngredient"   ) )
                    )
                )
            )
        )
    )






    //init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info.setFunctionName("onCreate")

        //debug
        msg.log("Entering in meal list.")






        // DISPLAY

        //create binding instance
        binding = LyoMealListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set RecycleView title
        categoryNbr = intent.getIntExtra("title_text",0)
        when(categoryNbr){
            CATEGORY_ENTREE  -> findViewById<TextView>(R.id.meal_list_title_text).text = "Entrées"
            CATEGORY_PLAT    -> findViewById<TextView>(R.id.meal_list_title_text).text = "Plats"
            CATEGORY_DESSERT -> findViewById<TextView>(R.id.meal_list_title_text).text = "Desserts"
            else -> err.log(false, "Invalid category number received in extra.")
        }






        // REQUEST MEAL LIST

        //create request
        val queue = Volley.newRequestQueue(this)
        val jsonRequest = JSONObject()
        jsonRequest.put("id_shop", 1)

        //set request process
        val stringRequest = JsonObjectRequest(
            Request.Method.POST,
            "http://test.api.catering.bluecodegames.com/menu",
            jsonRequest,



            //RESPONSE

            //response received
            Response.Listener { response ->
                msg.log("Response is: ${response.toString()}")

                //get raw json data
                meals = Gson().fromJson(response.toString(), MealInfo::class.java)

                //set RecycleView elements
                binding.mealListRecView.layoutManager = LinearLayoutManager(this)
                binding.mealListRecView.adapter = CategoryAdapter(
                    meals.data[categoryNbr].detailList
                ) { meal ->
                    val intent = Intent(this, MealActivity::class.java)
                    intent.putExtra("meal", meal as Serializable)
                    startActivity(intent)
                }
            },

            //error case
            Response.ErrorListener { _ ->
                err.log(false, "Unable to get request from \"http://test.api.catering.bluecodegames.com/menu\".")
            }
        )



        //FINISH REQUEST

        //push request in queue
        queue.add(stringRequest)
    }
}
