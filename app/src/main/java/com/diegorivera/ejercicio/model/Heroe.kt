package com.diegorivera.ejercicio.model

import com.google.gson.annotations.SerializedName

data class Heroe(
    val id: Int,
    val name: String,
    val localized_name: String,
    val primary_attr: String,
    val attack_type: String,
    val roles: List<String>,
    val legs: Int)

