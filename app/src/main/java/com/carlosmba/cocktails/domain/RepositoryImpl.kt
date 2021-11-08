package com.carlosmba.cocktails.domain

import com.carlosmba.cocktails.data.DataSource
import com.carlosmba.cocktails.data.model.Cocktail
import com.carlosmba.cocktails.vo.Resource

class RepositoryImpl(private val dataSource : DataSource) : Repository {
    override suspend fun getAllCocktails(cocktailName : String): Resource<List<Cocktail>> {
        return dataSource.getCocktailByName(cocktailName)
    }

    override suspend fun saveCocktailFavorite(cocktail: Cocktail) {
        dataSource.saveFavoriteCocktail(cocktail)
    }

    override suspend fun getAllCocktailsFavorites(): Resource<List<Cocktail>> = dataSource.getAllCocktailsFavorites()




}