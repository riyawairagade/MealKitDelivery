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
            Room.databaseBuilder(context.applicationContext, MealDatabase::class.java, "DataBase.db")
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Thread(Runnable { prepopulateDb(context, getInstance(context)) }).start()
                    }
                })
                .fallbackToDestructiveMigration()
                .build()

        private fun prepopulateDb(context: Context, db: MealDatabase) {
            val mealNameValues = arrayOf("meal1","meal2","meal3","meal4")
            val mealImageValues = arrayOf(R.drawable.outline_fastfood_24,R.drawable.outline_fastfood_24,R.drawable.outline_fastfood_24,R.drawable.outline_fastfood_24)
            val mealRecipeValues = arrayOf("meal1","meal2","meal3","meal4")
            val mealIngredientsValues = arrayOf("meal1","meal2","meal3","meal4")
            val mealPriceValues = arrayOf("100","300","500","600")

           // db.mealDao().insert(mealNameValues, mealImageValues, mealRecipeValues, mealIngredientsValues, mealPriceValues)
            db.mealDao().insert(listOf(Meal(mealName = "meal1", mealImage = R.drawable.outline_fastfood_24, mealIngredients = "meal1", mealPrice = "100", mealRecipe = "meal1")))
        }

 /*       fun getDatabase(context: Context): MealDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealDatabase::class.java,
                    "meal_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
  */
    }
}