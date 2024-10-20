package com.example.lab6pokemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lab6pokemon.interaction.CenterAlignedTopAppBarImagen
import com.example.lab6pokemon.interaction.CenterAlignedTopAppBarLista
import com.example.lab6pokemon.ui.theme.Lab6PokemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab6PokemonTheme {
                App()
            }
        }
    }
}

@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = backStackEntry?.destination?.route ?: "Screen1"

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "PantallaListaPokemon",
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = "PantallaListaPokemon") {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CenterAlignedTopAppBarLista(navController, innerPadding)
                }
            }

            composable(route = "PantallaImagenPokemon/{pokemonId}") { backStackEntry ->
                val arguments = backStackEntry.arguments
                Log.d("Navigation", "Arguments received: $arguments")

                val pokemonId = arguments?.getString("pokemonId")?.toIntOrNull()

                Log.d("Navigation Recibido", "ID received: $pokemonId")

                if (pokemonId != null) {
                    CenterAlignedTopAppBarImagen(navController, innerPadding, pokemonId)
                } else {
                    Log.e("Navigation", "Pokemon ID is null or invalid")
                }
            }

        }
    }
}
