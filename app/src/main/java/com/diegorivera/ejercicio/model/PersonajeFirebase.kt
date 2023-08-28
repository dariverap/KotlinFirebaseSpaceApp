package com.diegorivera.ejercicio.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class PersonajeFirebase(

    val name: String,
    val status: String,
    val species: String,
    val origin: String,
    val imgUrl: String)

