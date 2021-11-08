package com.carlosmba.cocktails.vo

import com.carlosmba.cocktails.data.WebService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    val retrofit  : WebService by lazy{
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(
            GsonBuilder().create()
        )).build().create(WebService::class.java)
    }

}