package com.carlosmba.cocktails.ui.viewmodel

import androidx.lifecycle.*
import com.carlosmba.cocktails.data.model.Cocktail
import com.carlosmba.cocktails.domain.Repository
import com.carlosmba.cocktails.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository : Repository) : ViewModel() {

    private val searchText = MutableLiveData<String>()

    init {
        setSearchText("margarita")
    }


    fun setSearchText(text : String){
        searchText.value = text
    }


    val fetchCocktailList = searchText.distinctUntilChanged().switchMap { name->
        liveData(Dispatchers.IO) {

            emit(Resource.Loading)

            try{
                emit(repository.getAllCocktails(name))
            }catch(e : Exception){
                emit(Resource.Failure(e))
            }
        }
    }


    fun saveCocktailFavorite(cocktail : Cocktail) = liveData(Dispatchers.IO){
        viewModelScope.launch {
            emit(Resource.Loading)

            try{
                emit(repository.saveCocktailFavorite(cocktail))

            }catch(e : Exception){
                emit(Resource.Failure(e))
            }
        }
    }


    val getAllCocktailFavorite = liveData(Dispatchers.IO){
        viewModelScope.launch {
            emit(Resource.Loading)

            try{
                emit(repository.getAllCocktailsFavorites())

            }catch(e : Exception){
                emit(Resource.Failure(e))
            }
        }
    }

}