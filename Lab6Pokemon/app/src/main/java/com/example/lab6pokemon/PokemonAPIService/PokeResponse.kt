package com.uvg.lab6pokemon.network

data class PokeResponse(val results: List<Pokemon>)

// Modelo para el Pokémon (simplificado)
data class Pokemon(
    val name: String,
    val url: String
)