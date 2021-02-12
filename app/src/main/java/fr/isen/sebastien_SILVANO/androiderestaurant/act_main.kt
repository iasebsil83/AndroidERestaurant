package fr.isen.sebastien_SILVANO.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoMainBinding



class act_main : AppCompatActivity(){



    //binding
    private lateinit var binding : LyoMainBinding



    //init
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = LyoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainAction = findViewById<Button>(R.id.entrees)

        binding.entrees.setOnClickListener{
            val toast = Toast.makeText(applicationContext, "entree", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}