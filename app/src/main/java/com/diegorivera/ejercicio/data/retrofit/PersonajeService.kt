package com.diegorivera.ejercicio.data.retrofit

import com.diegorivera.ejercicio.data.response.LugarListResponse
import com.diegorivera.ejercicio.model.Heroe
import com.diegorivera.ejercicio.model.Personaje
import retrofit2.http.GET


interface PersonajeService {
    @GET("character")
    suspend fun getPersonajes(): List<Personaje>
}
