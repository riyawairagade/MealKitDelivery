package com.example.mealkitdeliveryapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MealDao {

    @Query("SELECT * FROM meal")
    fun getAll(): LiveData<List<Meal>>

//    @Insert
//    fun insert(
//        meals: Array<String>,
//        mealImageValues: Array<Int>,
//        mealRecipeValues: Array<String>,
//        mealIngredientsValues: Array<String>,
//        mealPriceValues: Array<String>
//    )

    @Insert
    fun insert(meals: List<Meal>)
}