package fr.isen.sebastien_SILVANO.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoRecViewCellBinding


//binding
private lateinit var binding : LyoRecViewCellBinding



class CategoryAdapter(
    private val categories : List<String>,
    private val onItemClickListener:(String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    //info
    private var info : CodeInfo = CodeInfo("RecyclerViewCell","CategoryAdapter.kt")


    //init
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CategoryAdapter.CategoryViewHolder {

        //debug
        info.setFunctionName("onCreateViewHolder")
        Message(info).log("Created RecyclerViewCell.")

        return CategoryViewHolder(
            LyoRecViewCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }



    //viewHolder
    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cellTitle   : TextView = view.findViewById(R.id.cell_title)
        val cellContent : TextView = view.findViewById(R.id.cell_content)
    }


    //utilities
    override fun getItemCount() = categories.size



    //events
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.cellTitle.text   = categories[position]
        holder.cellContent.text = categories[position]
        holder.cellTitle.setOnClickListener(){

            //debug
            info.setFunctionName("onBindViewHolder")
            Message(info).log("Clicked on \"${categories[position]}\".")

            //call click function
            onItemClickListener(categories[position])
        }
    }
}