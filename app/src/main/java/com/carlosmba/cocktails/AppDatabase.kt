package com.carlosmba.cocktails

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carlosmba.cocktails.data.model.DrinkEntity
import com.carlosmba.cocktails.domain.DrinkDao

@Database(entities=[DrinkEntity::class], version=1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun drinkDao() : DrinkDao

    companion object{
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context : Context) : AppDatabase{
            return INSTANCE ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "table_drink").build()
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}