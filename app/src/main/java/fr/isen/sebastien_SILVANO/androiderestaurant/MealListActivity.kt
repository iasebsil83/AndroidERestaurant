package fr.isen.sebastien_SILVANO.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoMealListBinding


class MealListActivity : AppCompatActivity(){

    //info
    private val info : CodeInfo = CodeInfo("MealList", "MealListActivity.kt")

    //binding
    private lateinit var binding : LyoMealListBinding



    //init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info.setFunctionName("onCreate")



        //debug
        Message(info).log("Entering in MealList.")

        //log tests
        Message(info).log("This is a message.")
        Error(info).log(false, "This is a runtime error.")
        Error(info).log(true, "This is a fatal error.")



        //create binding instance
        binding = LyoMealListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set RecycleView title
        val name = intent.getStringExtra("title_text")
        findViewById<TextView>(R.id.title_text).text = name

        //set RecycleView elements
        binding.recView.layoutManager = LinearLayoutManager(this)
        binding.recView.adapter = CategoryAdapter(
                listOf(
                        "pates bolo",
                        "burger maison",
                        "salade",
                        "tomate",
                        "oignons"
                )
        ) { item ->
            val intent = Intent(this, MealActivity::class.java)
            intent.putExtra("meal", item)
            startActivity(intent)
        }
    }
}