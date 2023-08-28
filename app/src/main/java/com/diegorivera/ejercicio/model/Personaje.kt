package com.diegorivera.ejercicio.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Personaje")
@Parcelize
data class Personaje(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val hair: String,
    val origin: String,
    @SerializedName("img_url")
    val imgUrl: String,
    var isFavorite: Boolean = false) : Parcelable

