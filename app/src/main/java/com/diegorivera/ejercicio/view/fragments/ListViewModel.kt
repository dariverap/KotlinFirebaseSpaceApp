package com.diegorivera.ejercicio.view.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegorivera.ejercicio.data.repository.HeroesRepository
import com.diegorivera.ejercicio.data.repository.LugaresRepository
import com.diegorivera.ejercicio.data.repository.PersonajesRepository
import com.diegorivera.ejercicio.data.response.ApiResponse
import com.diegorivera.ejercicio.model.Heroe
import com.diegorivera.ejercicio.model.Lugar
import com.diegorivera.ejercicio.model.Personaje
import com.diegorivera.ejercicio.model.getData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    val repository = PersonajesRepository()
    val personajesList: MutableLiveData<List<Personaje>?> = MutableLiveData()

    fun getPersonajesFromService() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPersonajes()
            when (response) {
                is ApiResponse.Error -> {
                    // Manejar el error si es necesario
                    // Por ejemplo, puedes mostrar un mensaje de error en la interfaz de usuario
                }
                is ApiResponse.Success -> {
                    personajesList.postValue(response.data)
                }
            }
        }
    }
}