package com.diegorivera.ejercicio.data.repository

import com.diegorivera.ejercicio.data.response.ApiResponse
import com.diegorivera.ejercicio.data.response.LugarListResponse
import com.diegorivera.ejercicio.data.retrofit.ServiceInstance


class LugaresRepository {
    suspend fun getLugares(): ApiResponse<LugarListResponse>

    {
        return try{
            val response = ServiceInstance.getLugarService().getLugares()
            ApiResponse.Success(response)
        }catch(e:Exception){
            ApiResponse.Error(e)
        }

    }
}