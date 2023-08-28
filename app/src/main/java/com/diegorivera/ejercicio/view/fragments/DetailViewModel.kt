package com.diegorivera.ejercicio.view.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.diegorivera.ejercicio.data.db.PersonajeDataBase
import com.diegorivera.ejercicio.data.repository.PersonajesRepository
import com.diegorivera.ejercicio.model.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel (application: Application): AndroidViewModel(application) {

    private val repository: PersonajesRepository

    init{
        val db= PersonajeDataBase.getDataBase(application)
        repository = PersonajesRepository(db.PersonajeDao())
    }

    fun addFavorites(personaje: Personaje){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavorite(personaje)
        }
    }

    fun removeFavorite(personaje: Personaje) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFavorite(personaje)
        }
    }
}