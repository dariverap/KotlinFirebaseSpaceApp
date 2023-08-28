package com.diegorivera.ejercicio.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diegorivera.ejercicio.model.Personaje

@Database(entities = [Personaje::class], version = 1)
abstract class PersonajeDataBase: RoomDatabase() {

    abstract fun PersonajeDao() : PersonajeDao

    companion object{
        @Volatile
        private var instance : PersonajeDataBase? = null
        fun getDataBase(context: Context): PersonajeDataBase{
            if(instance == null){
                synchronized(this){
                    instance = buildDataBase(context)
                }
            }
            return instance!!
        }

        private fun buildDataBase(context: Context): PersonajeDataBase? {
            return Room.databaseBuilder(
                context.applicationContext,
                PersonajeDataBase::class.java,
                "personaje_database"
            ).build()
        }
    }
}