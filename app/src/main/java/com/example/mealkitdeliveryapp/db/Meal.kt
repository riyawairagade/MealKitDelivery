package com.example.mealkitdeliveryapp.db

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meal (
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo val mealName: String,
    @ColumnInfo val mealImage: Int,
    @ColumnInfo val mealRecipe: String,
    @ColumnInfo val mealIngredients: String,
    @ColumnInfo val mealPrice: String
)