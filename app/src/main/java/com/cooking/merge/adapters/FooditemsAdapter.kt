package com.cooking.merge.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cooking.merge.databinding.CardviewLayoutBinding
import com.cooking.merge.model.FooditemsModel
import kotlinx.android.synthetic.main.cardview_layout.view.*

class FooditemsAdapter(
    private var context: Context,
    var clickListener: OnFoodItemClickListener,
    private var fooditems: ArrayList<FooditemsModel>
) :
    RecyclerView.Adapter<FoodItemHolder>() {

    //viewbinding
    private lateinit var binding: CardviewLayoutBinding

    override fun getItemCount(): Int {
        return fooditems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemHolder {
        binding = CardviewLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return FoodItemHolder(binding.root)

    }

    override fun onBindViewHolder(holder: FoodItemHolder, position: Int) {
//        holder.icons.setImageResource(fooditems.get(position).iconsChar)
//        holder.title.text = fooditems.get(position).alphaChar

         holder.init(fooditems.get(position), clickListener)
    }

}

class FoodItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var icons: ImageView = itemView.icons_image
    var title: TextView = itemView.title

    fun init(item: FooditemsModel, action: OnFoodItemClickListener) {
        title.text = item.alphaChar
        icons.setImageResource(item.iconsChar)

        itemView.setOnClickListener {
            action.onItemClick(item, adapterPosition)
        }
    }

}

interface OnFoodItemClickListener {
    fun onItemClick(item: FooditemsModel, position: Int)
}


