package com.example.lab6pokemon.PokemonAPIService

import com.uvg.lab6pokemon.network.PokeResponse
import retrofit2.http.GET
import retrofit2.http.Query

// Definir la interfaz de PokeAPI
interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int): PokeResponse
}