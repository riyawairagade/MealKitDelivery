package com.example.mealkitdeliveryapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealkitdeliveryapp.databinding.ItemRowMealBinding
import com.example.mealkitdeliveryapp.db.Meal

class MealsAdapter : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    var data = listOf<Meal>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsAdapter.ViewHolder {
        return ViewHolder(
            ItemRowMealBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MealsAdapter.ViewHolder, position: Int) {
        val meal = data[position]

        with(holder.binding) {
            mealText.text = meal.mealName
            mealPrice.text = meal.mealPrice

        }
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(val binding: ItemRowMealBinding) : RecyclerView.ViewHolder(binding.root)
}