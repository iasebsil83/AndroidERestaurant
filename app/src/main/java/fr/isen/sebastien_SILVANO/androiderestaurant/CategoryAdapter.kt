package fr.isen.sebastien_SILVANO.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.sebastien_SILVANO.androiderestaurant.databinding.LyoRecViewCellBinding
import fr.isen.sebastien_SILVANO.androiderestaurant.log.CodeInfo
import fr.isen.sebastien_SILVANO.androiderestaurant.log.Message
import fr.isen.sebastien_SILVANO.androiderestaurant.mealInfo.MealInfoDetails



class CategoryAdapter(
        private val productInfo : List<MealInfoDetails>,
        private val onItemClickListener:(MealInfoDetails) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {



    //debug info
    private val info :CodeInfo = CodeInfo("CategoryAdapter", "CategoryAdapter.kt")

    //binding
    private lateinit var binding : LyoRecViewCellBinding



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
        val cellTitle   : TextView  = view.findViewById(R.id.cell_title)
        val cellContent : TextView  = view.findViewById(R.id.cell_content)
        val cellPict    : ImageView = view.findViewById(R.id.cell_pict)
        val cellCase    : View      = view.findViewById(R.id.cell_bg)
    }



    //utilities
    override fun getItemCount() = productInfo.size



    //events
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.cellTitle.text   = productInfo[position].name
        holder.cellContent.text = productInfo[position].ingr.map{ it.name }.joinToString(separator = ", ")
        if(
            !productInfo[position].picts.isNullOrEmpty() &&
            !productInfo[position].picts[0].isNullOrEmpty()
        ) {
            Picasso.get().load(
                    productInfo[position].picts[0]
            ).into(holder.cellPict)
        }
        holder.cellCase.setOnClickListener(){
            onItemClickListener(productInfo[position])
        }
    }
}