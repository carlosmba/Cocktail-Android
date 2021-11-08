package com.carlosmba.cocktails.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carlosmba.cocktails.data.model.DrinkEntity

@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink")
    fun getAllFavoriteDrinks() : List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDrink(drink: DrinkEntity)
}