package fr.isen.sebastien_SILVANO.androiderestaurant.ble

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.content.Intent
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

    //BLE gatt
    var BLEGatt: BluetoothGatt? = null



    //init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info.setFunctionName("onCreate")


        //CHECK RECEIVED INFO

        //get device info
        val device = intent.getParcelableExtra<BluetoothDevice>("BLEDeviceInfo")

        //go back to previous actvity if incorrect info received
        if(device == null) {
            val intent = Intent(this, BLEScanActivity::class.java)
            startActivity(intent)
        }



        //LAYOUT

        //init binding instance
        binding = LyoBleDeviceInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //display device info
        if (device?.name == null) {
            findViewById<TextView>(R.id.ble_device_name).text = device?.name
        } else {
            findViewById<TextView>(R.id.ble_device_name).text = "Undefined"
        }
        findViewById<TextView>(R.id.ble_device_info).text = device?.address.toString()

        //display connection status
        binding.bleDeviceStatus.text = getString(
                R.string.ble_device_status,
                getString(R.string.ble_device_status_connecting)
        )



        //CONNECTION

        //launch connection
        if (device != null) {
            connectToDevice(device)
        }
    }



    //connection
    private fun connectToDevice(device: BluetoothDevice) {
        BLEGatt = device.connectGatt(this, false, object : BluetoothGattCallback(){

            override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int){
                super.onConnectionStateChange(gatt,status,newState)
                connectionStateChange(newState, gatt)
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int){
                //
            }

            override fun onCharacteristicRead(
                gatt: BluetoothGatt,
                characteristic: BluetoothGattCharacteristic,
                status: Int
            ) {
                //
            }
        })

    }

    private fun connectionStateChange(newState: Int, gatt: BluetoothGatt?){
        BLEConnectionStates.getBLEConnectionStateFromState(newState)?.let {
            runOnUiThread {
                binding.bleDeviceStatus.text = getString(R.string.ble_device_status, getString(it.text))
            }
        }
    }
}