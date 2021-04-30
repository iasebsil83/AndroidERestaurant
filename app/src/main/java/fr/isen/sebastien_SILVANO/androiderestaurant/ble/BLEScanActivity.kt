package fr.isen.sebastien_SILVANO.androiderestaurant.ble

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.sebastien_SILVANO.androiderestaurant.R
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoBleScanBinding
import fr.isen.sebastien_SILVANO.androiderestaurant.log.CodeInfo
import fr.isen.sebastien_SILVANO.androiderestaurant.log.Error
import fr.isen.sebastien_SILVANO.androiderestaurant.log.Message






//request id
const val BLE__REQUEST_ENABLE = 1

//scan period (for timeout call)
const val BLE__SCAN_PERIOD: Long = 10000






class BLEScanActivity : AppCompatActivity() {






    //binding
    private lateinit var binding : LyoBleScanBinding

    //class info
    private val info : CodeInfo = CodeInfo("BLEScan", "ble/BLEScanActivity.kt")

    //BLE info
    private var isScanning : Boolean = false
    private var BLEavailable: Boolean = false
    private lateinit var BLEManager: BluetoothManager
    private var BLEAdapter: BluetoothAdapter? = null
    private var BLEScanner: BluetoothLeScanner? = null
    private lateinit var BLEHandler : Handler
    private var BLEScanList : MutableList<ScanResult> = mutableListOf()






    // INITIALIZATION
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info.setFunctionName("onCreate")



        // LAYOUT

        //init binding instance
        binding = LyoBleScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //bind scan button
        binding.bleLaunchScan.setOnClickListener {
            if(isScanning){
                BLEStopScan()
            }else{
                BLEStartScan()
            }
        }

        //bind also title
        binding.bleScanTitle.setOnClickListener {
            if(isScanning){
                BLEStopScan()
            }else{
                BLEStartScan()
            }
        }



        //BLE

        //set BLE variables
        setBLEVariables()

        //check BLE availability
        if(!BLEavailable){
            Toast.makeText(this, "BLE is not available for this device", Toast.LENGTH_SHORT).show()
            Error(info).log(false, "BLE is not available for this device.")
        }else{
            //debug
            Toast.makeText(this, "Ble is available.", Toast.LENGTH_SHORT).show()

            //check if BLE is enable
            promptEnableBluetooth()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setBLEVariables() {
        BLEManager = getSystemService(BluetoothManager::class.java)
        BLEAdapter = BLEManager?.adapter
        BLEavailable = (
            packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE) &&
            BLEManager != null
        )
        BLEScanner = BLEAdapter?.bluetoothLeScanner
        BLEHandler = Handler()
    }






    // PERMISSIONS
    private fun promptEnableBluetooth() {
        if(! (BLEAdapter!!.isEnabled) ){
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, BLE__REQUEST_ENABLE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            BLE__REQUEST_ENABLE -> {
                if (resultCode != Activity.RESULT_OK) {
                    promptEnableBluetooth()
                }
            }
        }
    }






    // SCAN

    //callback
    private val BLEScanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            info.setFunctionName("BLEScanCallBack")

            //add the device
            BLEScanList.add(result)
            Message(info).log("Added BLE device [${BLEScanList.last().device.address}].")

            BLEUpdateRecView()
        }
    }



    //start - stop
    private fun BLEStartScan() {
        //display
        binding.bleScanTitle.text = getString(R.string.BLEScan_pauseTitle);
        binding.bleLaunchScan.setImageResource(R.drawable.ic_pause_button)
        binding.bleScanProgress.visibility = View.VISIBLE

        //launch scanner
        BLEScanner?.let { scanner ->
            isScanning = true

            //launch timer
            BLEHandler.postDelayed(
                {
                    //scan timed out
                    BLEStopScan()
                },
                BLE__SCAN_PERIOD
            )

            //start scan
            scanner.startScan(BLEScanCallback)
        }
    }

    private fun BLEStopScan(){
        //display
        binding.bleScanTitle.text = getString(R.string.BLEScan_playTitle);
        binding.bleLaunchScan.setImageResource(R.drawable.ic_play_button)
        binding.bleScanProgress.visibility = View.INVISIBLE

        //stop scanner
        BLEScanner?.let { scanner ->
            isScanning = false

            //stop scan
            scanner.stopScan(BLEScanCallback)
        }
    }






    // DISPLAY
    private fun BLEUpdateRecView(){
        //update recycler view
        binding.bleScanRecView.layoutManager = LinearLayoutManager(this)
        binding.bleScanRecView.adapter = BLEScanAdapter(
                BLEScanList
        ) { result ->
            val intent = Intent(this, BLEDeviceInfoActivity::class.java)
            intent.putExtra("BLEDeviceInfo", result.toString())
            startActivity(intent)
        }
    }
}