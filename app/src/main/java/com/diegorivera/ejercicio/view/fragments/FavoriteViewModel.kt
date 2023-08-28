package com.diegorivera.ejercicio.view.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.diegorivera.ejercicio.data.db.PersonajeDataBase
import com.diegorivera.ejercicio.data.repository.PersonajesRepository
import com.diegorivera.ejercicio.model.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel (application: Application): AndroidViewModel(application){


    private val repository: PersonajesRepository
    private val _favorites: MutableLiveData<List<Personaje>> = MutableLiveData()
    val favorites: LiveData<List<Personaje>> = _favorites

    init{
        val db= PersonajeDataBase.getDataBase(application)
        repository = PersonajesRepository(db.PersonajeDao())
    }

    fun getFavorites(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getFavorites()
            _favorites.postValue(data)
        }

    }
}