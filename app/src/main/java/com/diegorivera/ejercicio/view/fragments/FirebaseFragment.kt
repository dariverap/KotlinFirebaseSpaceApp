package com.diegorivera.ejercicio.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import com.diegorivera.ejercicio.RVListAdapterFirebase
import com.diegorivera.ejercicio.databinding.FragmentFirebaseBinding
import com.diegorivera.ejercicio.view.AddPersonajeActivity

class FirebaseFragment : Fragment() {


    private lateinit var binding: FragmentFirebaseBinding
    private lateinit var adapter: RVListAdapterFirebase
    private lateinit var viewModel: FirestoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFirebaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RVListAdapterFirebase(emptyList()) { /* Handle item click here */ }
        viewModel = ViewModelProvider(this).get(FirestoreViewModel::class.java)

        binding.rvExploraList.adapter = adapter
        binding.rvExploraList.layoutManager =
            GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)

        viewModel.clothes.observe(viewLifecycleOwner, Observer { clothes ->
            adapter.updateData(clothes)
            adapter.notifyDataSetChanged()
        })

        viewModel.getFirebaseList()

        binding.btnAddPersonaje.setOnClickListener {
            val intent = Intent(requireContext(), AddPersonajeActivity::class.java)
            startActivity(intent)
        }
    }

}