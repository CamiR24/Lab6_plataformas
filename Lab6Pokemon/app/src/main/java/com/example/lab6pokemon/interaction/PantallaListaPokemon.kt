package com.example.lab6pokemon.interaction

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lab6pokemon.R
import com.example.lab6pokemon.ui.theme.Lab6PokemonTheme
import com.uvg.lab6pokemon.network.Pokemon
import com.uvg.lab6pokemon.network.RetrofitClient
import kotlinx.coroutines.launch
import coil.compose.AsyncImage

class PantallaListaPokemon : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab6PokemonTheme {
                    CenterAlignedTopAppBarLista(navController = rememberNavController(), innerPadding = PaddingValues())
                }
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBarLista(navController: NavHostController, innerPadding: PaddingValues) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        text = stringResource(R.string.main),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate("Screen1") }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Localized description",
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        ListaPokemon(navController, innerPadding)
    }
}

@Composable
fun ListaPokemon(navController: NavHostController, innerPadding: PaddingValues){
    val pokemonList = remember { mutableStateOf<List<Pokemon>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val response = RetrofitClient.apiService.getPokemonList(100)
            Log.d("Pokemon API Response", response.results.toString()) // Log para verificar los datos recibidos
            pokemonList.value = response.results
        }
    }

    LazyColumn (horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top) {
        items (items = pokemonList.value, key = {pokemon:Pokemon -> pokemon.id}) { pokemon:Pokemon ->
            PokemonEspecificado(pokemon, navController)
        }
    }
}

@Composable
fun PokemonEspecificado(
    pokemon: Pokemon,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .height(80.dp)
            .width(400.dp)
            .clickable {
                navController.navigate("pantallaImagenPokemon/${pokemon.id}")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Row (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color(0xFF1BC1DE)),
            verticalAlignment = Alignment.Bottom,
        ) {
            AsyncImage(
                model = pokemon.imageURLFront,
                contentDescription = "Imagen del Pokemon",
                modifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 10.dp)
                    .size(64.dp)
            )

            Text(
                text = pokemon.name,
                color = Color(0xFF006E81),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting1Preview() {
    Lab6PokemonTheme {
        CenterAlignedTopAppBarLista(navController = rememberNavController(), innerPadding = PaddingValues())
    }
}