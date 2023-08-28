package com.diegorivera.ejercicio.data.repository

import com.diegorivera.ejercicio.data.response.ApiResponse
import com.diegorivera.ejercicio.data.response.LugarListResponse
import com.diegorivera.ejercicio.data.retrofit.ServiceInstance
import com.diegorivera.ejercicio.model.Heroe


class HeroesRepository {
    suspend fun getHeroes(): ApiResponse<List<Heroe>> {
        return try {
            val response = ServiceInstance.getHeroeService().getHeroes()
            ApiResponse.Success(response)
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }
    }
}