package com.example.lab6pokemon.interaction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
    Surface(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        color = Color(0xFFECCCE2)
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Para Ti",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFBB4491),
            )
            LazyColumn (horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top) {
                item {
                    PokemonEspecificado(navController)
                    PokemonEspecificado(navController)
                    PokemonEspecificado(navController)
                    PokemonEspecificado(navController)
                }
            }
        }
    }
}

@Composable
fun PokemonEspecificado(navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .height(80.dp)
            .width(400.dp)
            .clickable {  },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            ) {
                /*Image(
                    painter = painterResource(R.drawable.leah),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                        .clipToBounds()
                )*/
            }
            Row (
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color(0xFFECCCE2)),
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = "Nombre",
                    color = Color(0xFFBB4491),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                )
                Text(
                    text = "Atributo",
                    color = Color(0xFFBB4491),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    /*Image(
                        painter = painterResource(R.drawable.like),
                        contentDescription = "Like",
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                            .align(Alignment.CenterEnd)
                            .clickable { navController.navigate("Match") }

                    )*/
                }
            }
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