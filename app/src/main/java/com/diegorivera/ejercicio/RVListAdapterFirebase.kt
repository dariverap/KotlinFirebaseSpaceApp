package com.diegorivera.ejercicio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diegorivera.ejercicio.databinding.ItemPersonajelistBinding
import com.diegorivera.ejercicio.model.PersonajeFirebase


class RVListAdapterFirebase(var personajes: List<PersonajeFirebase>, private val onClick: (PersonajeFirebase) -> Unit) : RecyclerView.Adapter<RVListAdapterFirebase.PersonajeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val binding = ItemPersonajelistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonajeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        holder.bind(personajes[position])
    }

    override fun getItemCount(): Int = personajes.size

    inner class PersonajeViewHolder(private val binding: ItemPersonajelistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(personaje: PersonajeFirebase) {
            Glide
                .with(binding.root.context)
                .load(personaje.imgUrl)
                .into(binding.imageHeroe)

            binding.txtNombre.text= personaje.name
            binding.txtEspecie.text = personaje.species
            binding.txtEstatus.text = personaje.status
            binding.root.setOnClickListener { onClick(personaje) }
        }
    }

    fun updateData(newPersonajes: List<PersonajeFirebase>) {
        personajes = newPersonajes
        notifyDataSetChanged()
    }

}