package com.diegorivera.ejercicio.data.retrofit

import com.diegorivera.ejercicio.data.response.LugarListResponse
import com.diegorivera.ejercicio.model.Heroe
import retrofit2.http.GET


interface HeroeService {
    @GET("heroes")
    suspend fun getHeroes(): List<Heroe>
}
