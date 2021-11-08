package com.carlosmba.cocktails.domain

import com.carlosmba.cocktails.data.model.Cocktail
import com.carlosmba.cocktails.vo.Resource

interface Repository {
    suspend fun getAllCocktails(cocktailName : String) : Resource<List<Cocktail>>
    suspend fun saveCocktailFavorite(cocktail : Cocktail)
    suspend fun getAllCocktailsFavorites() : Resource<List<Cocktail>>
}