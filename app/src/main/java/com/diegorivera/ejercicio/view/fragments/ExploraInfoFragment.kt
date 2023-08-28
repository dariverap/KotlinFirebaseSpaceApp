package com.diegorivera.ejercicio.view.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.diegorivera.ejercicio.R
import com.diegorivera.ejercicio.databinding.FragmentExploraFavoriteBinding
import com.diegorivera.ejercicio.databinding.FragmentExploraInfoBinding
import com.diegorivera.ejercicio.view.InicioActivity
import com.diegorivera.ejercicio.view.SplashScreenActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ExploraInfoFragment : Fragment() {

    private lateinit var binding: FragmentExploraInfoBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences= requireActivity().getSharedPreferences(
            InicioActivity.SESSION_PREFERENCE,
            AppCompatActivity.MODE_PRIVATE
        )
        firebaseAuth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentExploraInfoBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCloseSesion.setOnClickListener {
            firebaseAuth.signOut()
            sharedPreferences.edit().apply{
                putString(InicioActivity.EMAIL,"")
                    .apply()
            }
            val intent = Intent(requireActivity(), SplashScreenActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
    }

}