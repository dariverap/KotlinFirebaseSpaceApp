package com.diegorivera.ejercicio.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.diegorivera.ejercicio.databinding.ActivityInicioBinding
import androidx.core.widget.addTextChangedListener
import com.diegorivera.ejercicio.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.shashank.sony.fancytoastlib.FancyToast


class InicioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInicioBinding
    private lateinit var googlelauncher: ActivityResultLauncher<Intent>
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        sharedPreferences = getSharedPreferences("SESSION_PREFERENCES", MODE_PRIVATE)
        firebaseAuth = Firebase.auth
        googlelauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    signInFirebaseWithGoogle(account.idToken)
                }catch (e:Exception){

                }
            }
        }
    }

    private fun signInFirebaseWithGoogle(idToken: String?) {
        val authCredential = GoogleAuthProvider.getCredential(idToken,null)
        firebaseAuth.signInWithCredential(authCredential)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    val user: FirebaseUser = firebaseAuth.currentUser!!

                    sharedPreferences.edit().apply{
                        putString(EMAIL, user.email)
                            .apply()
                    }
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    FancyToast.makeText(this, "Bienvenido!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show()
                }else{
                    FancyToast.makeText(this, "Ocurrió un error", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show()
                }
            }
    }

    private fun setupViews(){
        binding.tilEmail.editText?.addTextChangedListener{text ->
            binding.btnIngresar.isEnabled=validateInputs(text.toString(), binding.tilPassword.editText?.text.toString())
        }
        binding.tilPassword.editText?.addTextChangedListener {text ->
            binding.btnIngresar.isEnabled=validateInputs(binding.tilEmail.editText?.text.toString(),text.toString())
        }
        binding.btnIngresar.setOnClickListener {
            //val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
            //finish()
            signInWithEmailPassword()

        }

        binding.btnGoogle.setOnClickListener {
            singInWithGoogle()
        }

        binding.btnSignUp.setOnClickListener {
            signUpWithEmailPassword()
        }
    }

    private fun signUpWithEmailPassword() {
        val email = binding.tilEmail.editText?.text.toString()
        val password = binding.tilPassword.editText?.text.toString()
        if (validateInputs(email,password)){
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        FancyToast.makeText(this,"Usuario creado correctamente !",
                            FancyToast.LENGTH_LONG,
                            FancyToast.SUCCESS,true).show()
                    }else {
                        FancyToast.makeText(this,"No se pudo crear el usuario !",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,true).show()
                    }

                }
        }else{
            FancyToast.makeText(this,"Credenciales no validas !",
                FancyToast.LENGTH_LONG,
                FancyToast.ERROR,true).show()
        }
    }

    private fun signInWithEmailPassword() {
        val email = binding.tilEmail.editText?.text.toString()
        val password = binding.tilPassword.editText?.text.toString()

        if(validateInputs(email, password)){
            signInFirebaseWithEmail(email, password)
        }else{
            FancyToast.makeText(this, "Por favor, ingrese un correo y contraseña válidos", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show()
        }
    }

    private fun signInFirebaseWithEmail(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    FancyToast.makeText(this,"El usuario no se encontró !",
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,true).show()
                }
            }
    }


    private fun singInWithGoogle() {
        val googleSingOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail().build()
        val client: GoogleSignInClient = GoogleSignIn.getClient(this,googleSingOptions)
        val intent = client.signInIntent
        googlelauncher.launch(intent)
    }

    private fun validateInputs(email: String, password: String): Boolean{
        val isEmailOk= email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordOk = password.isNotEmpty()
        return isEmailOk && isPasswordOk
    }


    companion object{
        const val SESSION_PREFERENCE: String= "SESSION_PREFERENCE"
        const val EMAIL: String= "email"
    }
    }