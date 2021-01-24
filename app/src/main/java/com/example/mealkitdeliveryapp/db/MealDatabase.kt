package com.example.mealkitdeliveryapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mealkitdeliveryapp.R

@Database(entities = [Meal::class], version = 1)
abstract class MealDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao

    companion object {
        @Volatile
        private var INSTANCE: MealDatabase? = null

        fun getInstance(context: Context): MealDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = createInstance(context)
                }
                return INSTANCE!!
            }
        }

        private fun createInstance(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MealDatabase::class.java,
                "DataBase.db"
            )
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Thread(Runnable { prepopulateDb(getInstance(context)) }).start()
                    }
                })
                .fallbackToDestructiveMigration()
                .build()

        private fun prepopulateDb(db: MealDatabase) {

            db.mealDao().insert(
                listOf(
                    Meal(
                        mealName = "Butter Chicken",
                        mealImage = R.drawable.outline_fastfood_24,
                        mealIngredients = "meal1",
                        mealPrice = "725",
                        mealRecipe = "meal1"
                    ),
                    Meal(
                        mealName = "Lamb Roganjosh",
                        mealImage = R.drawable.outline_fastfood_24,
                        mealIngredients = "meal1",
                        mealPrice = "800",
                        mealRecipe = "meal1"
                    ),
                    Meal(
                        mealName = "Terriyaki Salmon",
                        mealImage = R.drawable.outline_fastfood_24,
                        mealIngredients = "meal1",
                        mealPrice = "850",
                        mealRecipe = "meal1"
                    ),
                    Meal(
                        mealName = "Chicken Parm",
                        mealImage = R.drawable.outline_fastfood_24,
                        mealIngredients = "meal1",
                        mealPrice = "725",
                        mealRecipe = "meal1"
                    )

                )
            )
        }


    }
}