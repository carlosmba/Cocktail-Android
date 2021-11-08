package com.carlosmba.cocktails.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cocktail(
    @SerializedName("idDrink")
    val cocktailId : Long = 0L,
    @SerializedName("strDrinkThumb")
    val image: String = "",
    @SerializedName("strDrink")
    val name: String = "",
    @SerializedName("strInstructions")
    val description: String = "",
    @SerializedName("strAlcoholic")
    val alcoholic : String = ""
    ) : Parcelable

data class CocktailList(val drinks : List<Cocktail>)

@Entity(tableName="drink")
data class DrinkEntity(@PrimaryKey val drinkId : Long,
                       @ColumnInfo(name="drink_image")
                       val image: String = "",
                       @ColumnInfo(name="drink_name")
                       val name: String = "",
                       @ColumnInfo(name="drink_description")
                       val description: String = "",
                       @ColumnInfo(name="drink_has_alcoholic")
                       val alcoholic : String = ""
                       )
fun Cocktail.asDrinkEntity() : DrinkEntity = DrinkEntity(cocktailId, image, name, description, alcoholic)

fun List<DrinkEntity>.asCocktailList() : List<Cocktail>  = map {
        Cocktail(it.drinkId, it.image, it.name, it.description, it.alcoholic)
    }

