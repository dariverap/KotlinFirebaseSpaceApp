package com.diegorivera.ejercicio.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.diegorivera.ejercicio.databinding.FragmentDetailBinding
import com.diegorivera.ejercicio.model.Personaje
import com.shashank.sony.fancytoastlib.FancyToast

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    val args: DetailFragmentArgs by navArgs()
    private lateinit var personaje: Personaje

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        personaje = args.personaje
        viewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide
            .with(binding.root.context)
            .load(personaje.imgUrl)
            .into(binding.imgPersonaje)
        binding.txtNombre.text= personaje.name
        binding.txtOrigen.text = personaje.origin
        binding.txtGenero.text = personaje.gender
        binding.txtEspecie.text = personaje.species
        binding.txtEstatus.text = personaje.status
        binding.btnAddFavorite.apply {
            visibility = if (personaje.isFavorite) View.GONE else View.VISIBLE
        }
        binding.btnAddFavorite.setOnClickListener {
            personaje.isFavorite = true
            viewModel.addFavorites(personaje)
            FancyToast.makeText(requireContext(),"Elemento agregado a favoritos!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
        }
        binding.btnDeleteFavorite.apply {
            visibility= if (!personaje.isFavorite) View.GONE else View.VISIBLE
        }
        binding.btnDeleteFavorite.setOnClickListener {
            personaje.isFavorite = false
            viewModel.removeFavorite(personaje)
            FancyToast.makeText(requireContext(),"Elemento eliminado!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
            navigatetoDetailList()
        }
    }

    private fun navigatetoDetailList(){
        val action = DetailFragmentDirections.actionDetailFragmentToExploraFavoriteFragment()
        findNavController().navigate(action)
    }
}