package com.example.mealkitdeliveryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mealkitdeliveryapp.databinding.ActivityMainBinding
import com.example.mealkitdeliveryapp.databinding.ActivityMealsListBinding
import com.example.mealkitdeliveryapp.db.MealDatabase

class MealsListActivity : AppCompatActivity() {

    private lateinit var db: MealDatabase
    private lateinit var binding: ActivityMealsListBinding

    private val mealsAdapter = MealsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = MealDatabase.getInstance(this)
        binding = ActivityMealsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db.mealDao().getAll().observe(this) {
            mealsAdapter.data = it
        }

        binding.mealRecycler.adapter = mealsAdapter

    }
}