package com.carlosmba.cocktails.data

import android.util.Log
import com.carlosmba.cocktails.AppDatabase
import com.carlosmba.cocktails.data.model.Cocktail
import com.carlosmba.cocktails.data.model.asCocktailList
import com.carlosmba.cocktails.data.model.asDrinkEntity
import com.carlosmba.cocktails.vo.Resource
import com.carlosmba.cocktails.vo.RetrofitClient

class DataSource(private val appDatabase : AppDatabase) {



    suspend fun getCocktailByName(name : String) : Resource<List<Cocktail>>{
        return Resource.Success(RetrofitClient.retrofit.getCocktailByName(name).drinks)
    }

    suspend fun saveFavoriteCocktail(cocktail : Cocktail){
        appDatabase.drinkDao().insertDrink(cocktail.asDrinkEntity())
    }

    suspend fun getAllCocktailsFavorites() : Resource<List<Cocktail>> = Resource.Success(appDatabase.drinkDao().getAllFavoriteDrinks().asCocktailList())

}