package com.carlosmba.cocktails.data

import com.carlosmba.cocktails.data.model.CocktailList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search.php")
    suspend fun getCocktailByName(@Query("s") cocktailName : String) : CocktailList
}