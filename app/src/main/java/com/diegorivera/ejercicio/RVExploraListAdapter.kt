package com.diegorivera.ejercicio
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView;
import com.diegorivera.ejercicio.databinding.ItemPersonajelistBinding
import com.diegorivera.ejercicio.model.Personaje

class RVExploraListAdapter(var personajes: List<Personaje>,val onClick:(Personaje)->Unit) : RecyclerView.Adapter<ExploraVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploraVH {
        val binding = ItemPersonajelistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExploraVH(binding,onClick)
    }

    override fun getItemCount(): Int = personajes.size

    override fun onBindViewHolder(holder: ExploraVH, position: Int) {
        holder.bind(personajes[position])
    }

    fun updatePersonajes(newPersonajes: List<Personaje>) {
        personajes = newPersonajes
        notifyDataSetChanged()
    }
}

class ExploraVH(private val binding: ItemPersonajelistBinding,val onClick:(Personaje)->Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(personaje: Personaje) {
        Glide
            .with(binding.root.context)
            .load(personaje.imgUrl)
            .into(binding.imageHeroe)
        binding.imageHeroe.setImageResource(R.drawable.atronaut)
        //binding.txtPuntuacion.text = "${heroe.puntuacion}"
        binding.txtNombre.text = personaje.name
        binding.txtEspecie.text = personaje.species
        binding.txtEstatus.text = personaje.status
        binding.root.setOnClickListener {
            onClick(personaje)
        }
    }
}