package fr.isen.sebastien_SILVANO.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoMainBinding
import kotlin.jvm.java as java


class MainActivity : AppCompatActivity(){



    //binding
    private lateinit var binding : LyoMainBinding
    private val info : CodeInfo = CodeInfo("Main", "MainActivity.kt")



    //init
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        info.setFunctionName("onCreate")



        //init binding instance
        binding = LyoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //bind menu button 1 : ENTREES
        binding.entrees.setOnClickListener{
            Toast.makeText(applicationContext, "entree", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MealListActivity::class.java)
            intent.putExtra("title_text", "Entrées")
            startActivity(intent)
        }

        //bind menu button 2 : PLATS
        binding.plats.setOnClickListener{
            Toast.makeText(applicationContext, "entree", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MealListActivity::class.java)
            intent.putExtra("title_text", "Plats")
            startActivity(intent)
        }

        //bind menu button 3 : DESSERT
        binding.desserts.setOnClickListener{
            Toast.makeText(applicationContext, "entree", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MealListActivity::class.java)
            intent.putExtra("title_text", "Desserts")
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        info.setFunctionName("onDestroy")

        //debug
        Message(info).log("Destroyed main activity.")
    }
}