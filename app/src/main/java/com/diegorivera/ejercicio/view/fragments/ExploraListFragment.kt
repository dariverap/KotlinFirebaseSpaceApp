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

import com.diegorivera.ejercicio.RVExploraListAdapter

import com.diegorivera.ejercicio.databinding.FragmentExploraListBinding

class ExploraListFragment : Fragment() {

    private lateinit var binding: FragmentExploraListBinding
    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentExploraListBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVExploraListAdapter(listOf()){ personaje->
            val exploraDetailDirection=ExploraListFragmentDirections.actionExploraListFragmentToDetailFragment(personaje)
           findNavController().navigate(exploraDetailDirection)
        }
        binding.rvExploraList.adapter = adapter
        binding.rvExploraList.layoutManager = GridLayoutManager(requireContext(),1,RecyclerView.VERTICAL,false )
        viewModel.personajesList.observe(requireActivity()){
            if (it != null) {
                adapter.personajes=it
            }
            adapter.notifyDataSetChanged()
        }
        viewModel.getPersonajesFromService()
    }


}





















