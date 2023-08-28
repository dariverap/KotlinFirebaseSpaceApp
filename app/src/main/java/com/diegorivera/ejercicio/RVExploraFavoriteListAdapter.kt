package com.diegorivera.ejercicio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegorivera.ejercicio.databinding.ItemFavoriteBinding
import com.diegorivera.ejercicio.databinding.ItemHerolistBinding
import com.diegorivera.ejercicio.model.Heroe

class RVExploraFavoriteListAdapter (var heroes: List<Heroe>) : RecyclerView.Adapter<ExploraFavoriteVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploraFavoriteVH {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExploraFavoriteVH(binding)
    }

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: ExploraFavoriteVH, position: Int) {
        holder.bind(heroes[position])
    }

    fun updateHeroes(newHeroes: List<Heroe>) {
        heroes = newHeroes
        notifyDataSetChanged()
    }
}


class ExploraFavoriteVH(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(heroe: Heroe) {
        binding.imageHeroe.setImageResource(R.drawable.heroe_dota2)
        //binding.txtPuntuacion.text = "${heroe.puntuacion}"
        binding.txtNombre.text = heroe.localized_name
        binding.txtPuntuacion.text = heroe.id.toString()
        binding.txtAtaque.text = heroe.attack_type
        binding.txtRoles.text = heroe.roles.toString()
        binding.txtDescripcion.text = when (heroe.primary_attr) {
            "str" -> "Fuerza"
            "agi" -> "Agilidad"
            "int" -> "Inteligencia"
            else -> "Descripción no disponible" // Opcional, si hay un valor inesperado
        }
    }
}