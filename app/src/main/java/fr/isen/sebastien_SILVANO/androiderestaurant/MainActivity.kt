package fr.isen.sebastien_SILVANO.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.sebastien_SILVANO.androiderestaurant.ble.BLEScanActivity
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoMainBinding
import fr.isen.sebastien_SILVANO.androiderestaurant.log.CodeInfo
import fr.isen.sebastien_SILVANO.androiderestaurant.log.Error
import fr.isen.sebastien_SILVANO.androiderestaurant.log.Message
import kotlin.jvm.java as java



//category
const val CATEGORY_ENTREE  :Int = 0
const val CATEGORY_PLAT    :Int = 1
const val CATEGORY_DESSERT :Int = 2



class MainActivity : AppCompatActivity(){



    //binding
    private lateinit var binding : LyoMainBinding

    //debug info
    private val info : CodeInfo = CodeInfo("Main", "MainActivity.kt")
    private val msg  : Message  = Message(info)
    private val err  : Error    = Error  (info)



    //init
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        info.setFunctionName("onCreate")



        // LAYOUT

        //init binding instance
        binding = LyoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // BUTTONS

        //bind menu BLE scan button
        binding.bleScan.setOnClickListener{
            val intent = Intent(this, BLEScanActivity::class.java)
            startActivity(intent)
        }

        //bind menu button 1 : ENTREES
        binding.entrees.setOnClickListener{
            Toast.makeText(applicationContext, "Entrées", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MealListActivity::class.java)
            intent.putExtra("title_text", CATEGORY_ENTREE)
            startActivity(intent)
        }

        //bind menu button 2 : PLATS
        binding.plats.setOnClickListener{
            Toast.makeText(applicationContext, "Plats", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MealListActivity::class.java)
            intent.putExtra("title_text", CATEGORY_PLAT)
            startActivity(intent)
        }

        //bind menu button 3 : DESSERT
        binding.desserts.setOnClickListener{
            Toast.makeText(applicationContext, "Desserts", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MealListActivity::class.java)
            intent.putExtra("title_text", CATEGORY_DESSERT)
            startActivity(intent)
        }
    }



    //end
    override fun onDestroy() {
        super.onDestroy()
        info.setFunctionName("onDestroy")

        //debug
        msg.log("Destroyed main activity.")
    }
}
