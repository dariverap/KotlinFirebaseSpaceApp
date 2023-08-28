package com.diegorivera.ejercicio.data.db

import androidx.room.*
import com.diegorivera.ejercicio.model.Personaje
@Dao
interface PersonajeDao {

    @Query("SELECT * FROM personaje ORDER BY id DESC")
    fun getFavorites():List<Personaje>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(personaje: Personaje)

    @Delete
    suspend fun removeFavorite(personaje: Personaje)
}