package com.diegorivera.ejercicio.view.fragments

import android.app.Application
import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diegorivera.ejercicio.model.PersonajeFirebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirestoreViewModel(application: Application) : AndroidViewModel(application) {
    private val firestore: FirebaseFirestore = Firebase.firestore
    private val _personajes: MutableLiveData<List<PersonajeFirebase>> = MutableLiveData()
    val clothes: LiveData<List<PersonajeFirebase>> = _personajes

    fun getFirebaseList() {
        firestore.collection("personaje").get()
            .addOnSuccessListener { result ->
                val clothesList = mutableListOf<PersonajeFirebase>()
                for (document in result.documents) {
                    val clotheFirebase = PersonajeFirebase(
                        name = document.getString("name") ?: "",
                        species = document.getString("species") ?: "",
                        imgUrl = document.getString("img")?: "",
                        origin = document.getString("origin")?: "",
                        status = document.getString("status") ?: ""
                    )
                    clothesList.add(clotheFirebase)
                }
                _personajes.value = clothesList
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error getting documents: ", exception)
            }
    }



}