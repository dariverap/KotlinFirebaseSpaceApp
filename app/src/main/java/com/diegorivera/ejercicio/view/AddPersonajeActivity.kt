package com.diegorivera.ejercicio.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.diegorivera.ejercicio.R
import com.diegorivera.ejercicio.databinding.ActivityAddPersonajeBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.shashank.sony.fancytoastlib.FancyToast

class AddPersonajeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPersonajeBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPersonajeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = Firebase.firestore
        storage = Firebase.storage


        binding.btnAdd.setOnClickListener {
            val name: String = binding.txtNombre.editText?.text.toString()
            val species: String = binding.txtEspecie.editText?.text.toString()
            val img:String = binding.imgUrl.editText?.text.toString()
            val origin: String = binding.txtOrigen.editText?.text.toString()
            val status: String = binding.txtEstatus.editText?.text.toString()
            if (name.isNotEmpty() && species.isNotEmpty() && origin.isNotEmpty()&& status.isNotEmpty()){
                uploadImageAndClotheData(name,species,origin,status)
            }
            cleanTextFields()
        }
        binding.btnSelectImage.setOnClickListener {
            openImageSelector()
        }
    }

    private fun uploadImageAndClotheData(name: String, species: String, origin: String,status: String) {
        val storageRef = storage.reference
        val imageRef = storageRef.child("images/${selectedImageUri?.lastPathSegment}")

        val uploadTask = imageRef.putFile(selectedImageUri!!)

        uploadTask.addOnSuccessListener {

            imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                val imgUrl = downloadUri.toString()

                // Now you can save the rest of the clothe data along with the image URL to Firestore
                val personaje = hashMapOf(
                    "name" to name,
                    "species" to species,
                    "origin" to origin,
                    "status" to status,
                    "img" to imgUrl
                )
                firestore.collection("personaje")
                    .add(personaje)
                    .addOnSuccessListener {
                        FancyToast.makeText(this, "Agregado correctamente con id: ${it.id}", FancyToast.LENGTH_SHORT,
                            FancyToast.SUCCESS,true).show()
                    }
                    .addOnFailureListener {
                        FancyToast.makeText(this,"No se agregó el elemento !",
                            FancyToast.LENGTH_LONG,
                            FancyToast.INFO,true).show()
                    }
            }
        }.addOnFailureListener {
            FancyToast.makeText(this,"Error al subir la imagen",
                FancyToast.LENGTH_LONG,
                FancyToast.ERROR,true).show()
        }
    }

    private fun openImageSelector() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICKER_REQUEST)
    }

    companion object {
        private const val IMAGE_PICKER_REQUEST = 123
    }

    private fun cleanTextFields(){
        // Limpiar los campos después de agregar la prenda
        binding.txtNombre.editText?.setText("")
        binding.txtOrigen.editText?.setText("")
        binding.txtEspecie.editText?.setText("")
        binding.txtEstatus.editText?.setText("")
        binding.txtNombre.editText?.requestFocus()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICKER_REQUEST && resultCode == RESULT_OK) {
            selectedImageUri = data?.data
            Log.d("ImageCapture", "Selected Image Uri: $selectedImageUri")
        }
    }
}