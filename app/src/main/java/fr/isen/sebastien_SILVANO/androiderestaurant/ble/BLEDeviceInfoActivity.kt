package fr.isen.sebastien_SILVANO.androiderestaurant.ble

import android.bluetooth.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.sebastien_SILVANO.androiderestaurant.R
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoBleDeviceInfoBinding
import fr.isen.sebastien_SILVANO.androiderestaurant.log.CodeInfo
import fr.isen.sebastien_SILVANO.androiderestaurant.log.Error
import fr.isen.sebastien_SILVANO.androiderestaurant.log.Message



class BLEDeviceInfoActivity : AppCompatActivity() {



    //debug info
    private val info : CodeInfo = CodeInfo("BLEDeviceInfo", "ble/BLEDeviceInfoActivity.kt")
    private val msg  : Message = Message(info)
    private val err  : Error   = Error  (info)

    //binding
    private lateinit var binding : LyoBleDeviceInfoBinding

    //BLE gatt
    var BLEGatt : BluetoothGatt? = null
    var status  : String         = "Status : unknown"



    //init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info.setFunctionName("onCreate")


        //CHECK RECEIVED INFO

        //get device info
        val device = intent.getParcelableExtra<BluetoothDevice>("BLEDeviceInfo")

        //go back to previous activity if incorrect info received
        if(device == null) {
            val intent = Intent(this, BLEScanActivity::class.java)
            startActivity(intent)
        }



        //LAYOUT

        //init binding instance
        binding = LyoBleDeviceInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //display device info
        val name = intent.getStringExtra("BLEDeviceName")
        if (name != null) {
            findViewById<TextView>(R.id.ble_device_name).text = name
        } else {
            findViewById<TextView>(R.id.ble_device_name).text = "Undefined"
        }

        //display connection status
        binding.bleDeviceStatus.text = getString(
                      R.string.ble_device_status,
            getString(R.string.ble_device_status_connecting)
        )



        //CONNECTION

        //launch connection
        BLEGatt = device?.connectGatt(this, false, gattCallback)
        //BLEGatt?.connect() //normally used only for reconnection
    }






    //EXPANDABLE RECYCLER VIEW

    //link all handlers with the adapter
    private val gattCallback = object : BluetoothGattCallback() {

        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int){

            when(newState){
                BluetoothProfile.STATE_CONNECTED -> {
                    runOnUiThread {
                        binding.bleDeviceStatus.text = "Status : connected"
                    }
                    BLEGatt?.discoverServices()
                }
                BluetoothProfile.STATE_DISCONNECTED -> {
                    runOnUiThread {
                        binding.bleDeviceStatus.text = "Status : disconnected"
                    }
                }
            }
        }

        override fun onCharacteristicRead(
                gatt: BluetoothGatt?,
                characteristic: BluetoothGattCharacteristic?,
                status: Int
        ) {
            super.onCharacteristicRead(gatt, characteristic, status)
            runOnUiThread {
                binding.bleServicesRecView.adapter?.notifyDataSetChanged()
            }
        }

        override fun onCharacteristicWrite(
                gatt: BluetoothGatt?,
                characteristic: BluetoothGattCharacteristic?,
                status: Int
        ) {
            super.onCharacteristicWrite(gatt, characteristic, status)
            runOnUiThread {
                binding.bleServicesRecView.adapter?.notifyDataSetChanged()
            }
        }
        override fun onCharacteristicChanged(
                gatt: BluetoothGatt,
                characteristic: BluetoothGattCharacteristic
        ) {
            super.onCharacteristicChanged(gatt, characteristic)
            runOnUiThread {
                binding.bleServicesRecView.adapter?.notifyDataSetChanged()
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)
            runOnUiThread {
                binding.bleServicesRecView.adapter = BLEServiceAdapter(
                    gatt,
                    gatt?.services?.map {
                        BLEService(
                            it.uuid.toString(),
                            it.characteristics
                        )
                    }?.toMutableList() ?: arrayListOf(), this@BLEDeviceInfoActivity
                )
                binding.bleServicesRecView.layoutManager = LinearLayoutManager(this@BLEDeviceInfoActivity)
            }
        }
    }
}
