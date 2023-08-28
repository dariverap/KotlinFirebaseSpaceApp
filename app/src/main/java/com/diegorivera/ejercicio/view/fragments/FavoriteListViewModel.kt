package com.diegorivera.ejercicio.view.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegorivera.ejercicio.data.repository.HeroesRepository
import com.diegorivera.ejercicio.data.response.ApiResponse
import com.diegorivera.ejercicio.model.Heroe
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavoriteListViewModel : ViewModel() {

    val repository = HeroesRepository()
    val heroesList: MutableLiveData<List<Heroe>> = MutableLiveData<List<Heroe>>()

    fun getHeroesFromService() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getHeroes()
            when (response) {
                is ApiResponse.Success -> {
                    val allHeroes = response.data.toList()
                    val selectedHeroes = allHeroes.shuffled().take(2) // Selecciona 1 héroes aleatorios
                    heroesList.postValue(selectedHeroes)
                }
                is ApiResponse.Error -> {

                }
            }
        }
    }
}