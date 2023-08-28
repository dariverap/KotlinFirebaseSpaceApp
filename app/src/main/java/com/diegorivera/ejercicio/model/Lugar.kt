package com.diegorivera.ejercicio.model

import com.google.gson.annotations.SerializedName

data class Lugar(
    @SerializedName("url_image")
    var urlImagen: String,
    val nombre: String,
    val descripcion: String,
    val ubicacion:  String,
    val puntuacion: Int,
    @SerializedName("qr_code")
    val qrCode: String = ""
)

fun getData():List<Lugar> =
    listOf(
        Lugar("","Parque Nacional","Lugar bonito en EEUU","Oklahoma",10,""),
        Lugar("","Parque Nacional","Lugar bonito en EEUU","Oklahoma",10,""),
        Lugar("","Parque Nacional","Lugar bonito en EEUU","Oklahoma",10,""),
        Lugar("","Parque Nacional","Lugar bonito en EEUU","Oklahoma",10,""),
        Lugar("","Parque Nacional","Lugar bonito en EEUU","Oklahoma",10,""),
        Lugar("","Parque Nacional","Lugar bonito en EEUU","Oklahoma",10,""),
        Lugar("","Parque Nacional","Lugar bonito en EEUU","Oklahoma",10,""),
        Lugar("","Parque Nacional","Lugar bonito en EEUU","Oklahoma",10,""),
        Lugar("","Parque Nacional","Lugar bonito en EEUU","Oklahoma",10,""),
    )
