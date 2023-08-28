package com.diegorivera.ejercicio.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.diegorivera.ejercicio.R
import com.diegorivera.ejercicio.RVExploraFavoriteListAdapter
import com.diegorivera.ejercicio.RVExploraListAdapter
import com.diegorivera.ejercicio.databinding.FragmentExploraFavoriteBinding
import com.diegorivera.ejercicio.databinding.FragmentExploraListBinding


class ExploraFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentExploraFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(FavoriteViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentExploraFavoriteBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVExploraListAdapter(listOf()){ personaje->
            val exploraDetailDirection= ExploraFavoriteFragmentDirections.actionExploraFavoriteFragmentToDetailFragment(personaje)
            findNavController().navigate(exploraDetailDirection)
        }
        binding.rvExploraList.adapter = adapter
        binding.rvExploraList.layoutManager = GridLayoutManager(requireContext(),1,RecyclerView.VERTICAL,false )
        viewModel.favorites.observe(requireActivity()){ personajes ->
            if(personajes.isNullOrEmpty()){
                binding.rvExploraList.visibility = View.GONE
                //binding.tvEmptyListMessage.visibility = View.VISIBLE
                //binding.imgEmpty.visibility = View.VISIBLE
            }else{
                personajes?.let {
                    adapter.personajes = it
                    adapter.notifyDataSetChanged()
                }
            }
        }
        viewModel.getFavorites()
    }


}