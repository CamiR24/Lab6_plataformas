package com.uvg.lab6pokemon.network

data class PokeResponse(val results: List<Pokemon>)

// Modelo para el Pokémon (simplificado)
data class Pokemon(
    val name: String,
    val id: Int
) {
    val imageURLFront: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    val imageURLBack: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/$id.png"
    val imageURLShinyFront: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png"
    val imageURLShinyBack: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/$id.png"
}