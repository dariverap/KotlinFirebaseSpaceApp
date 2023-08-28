package com.diegorivera.ejercicio.data.repository

import com.diegorivera.ejercicio.data.db.PersonajeDao
import com.diegorivera.ejercicio.data.response.ApiResponse
import com.diegorivera.ejercicio.data.response.LugarListResponse
import com.diegorivera.ejercicio.data.retrofit.ServiceInstance
import com.diegorivera.ejercicio.model.Heroe
import com.diegorivera.ejercicio.model.Personaje


class PersonajesRepository(val personajeDao: PersonajeDao? = null) {
    suspend fun getPersonajes(): ApiResponse<List<Personaje>> {
        return try {
            val response = ServiceInstance.getPersonjeService().getPersonajes()
            ApiResponse.Success(response)
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }
    }

    suspend fun addFavorite(personaje: Personaje){
        personajeDao?.let {
            it.addFavorite(personaje)
        }
    }

    suspend fun removeFavorite(personaje: Personaje) {
        personajeDao?.let {
            it.removeFavorite(personaje)
        }
    }

    fun getFavorites(): List<Personaje>{
        personajeDao?.let {
            return it.getFavorites()
        } ?: kotlin.run {
            return listOf()
        }
    }
}