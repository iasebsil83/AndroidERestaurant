package fr.isen.sebastien_SILVANO.androiderestaurant.ble

import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.sebastien_SILVANO.androiderestaurant.R
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoBleRecViewCellBinding
import fr.isen.sebastien_SILVANO.androiderestaurant.log.CodeInfo
import fr.isen.sebastien_SILVANO.androiderestaurant.log.Message
import fr.isen.sebastien_SILVANO.androiderestaurant.log.Error



//binding
private lateinit var binding : LyoBleRecViewCellBinding



class BLEScanAdapter(
        private val scanList : MutableList<ScanResult>,
        private val onItemClickListener:(ScanResult) -> Unit
) : RecyclerView.Adapter<BLEScanAdapter.BLEScanViewHolder>() {

    //info
    private val info : CodeInfo = CodeInfo("BLEScanAdapter", "ble/BLEScanAdapter.kt")
    private val msg  : Message  = Message(info)
    private val err  : Error    = Error  (info)


    //init
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : BLEScanAdapter.BLEScanViewHolder {

        //debug
        info.setFunctionName("onCreateViewHolder")
        msg.log("Created RecyclerViewCell.")

        return BLEScanViewHolder(
            LyoBleRecViewCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }



    //viewHolder
    class BLEScanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cellTitle   : TextView = view.findViewById(R.id.ble_cell_title)
        val cellContent : TextView = view.findViewById(R.id.ble_cell_content)
        val cellCase    : View     = view.findViewById(R.id.ble_cell_bg)
    }


    //utilities
    override fun getItemCount() = scanList.size



    //events
    override fun onBindViewHolder(holder: BLEScanViewHolder, position: Int) {
        holder.cellTitle.text   = scanList[position].scanRecord?.deviceName
        holder.cellContent.text = scanList[position].device.address
        holder.cellCase.setOnClickListener(){
            onItemClickListener(scanList[position])
        }
    }
}
