package fr.isen.sebastien_SILVANO.androiderestaurant.ble

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import fr.isen.sebastien_SILVANO.androiderestaurant.R
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoBleDeviceInfoBinding
import fr.isen.sebastien_SILVANO.androiderestaurant.log.CodeInfo



class BLEDeviceInfoActivity : AppCompatActivity() {



    //debug info
    private val info : CodeInfo = CodeInfo("BLEDeviceInfo", "ble/BLEDeviceInfoActivity.kt")

    //binding
    private lateinit var binding : LyoBleDeviceInfoBinding



    //init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info.setFunctionName("onCreate")

        //init binding instance
        binding = LyoBleDeviceInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get device info
        val deviceInfo = intent.getStringExtra("BLEDeviceInfo")

        //display device info
        findViewById<TextView>(R.id.ble_device_name).text = deviceInfo
    }
}