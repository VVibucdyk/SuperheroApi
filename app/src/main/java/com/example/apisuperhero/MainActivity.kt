package com.example.apisuperhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.apisuperhero.ui.theme.ApiSuperHeroTheme
import model.Superhero
import viewmodel.SuperheroViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    private val viewModel: SuperheroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiSuperHeroTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val apiKey = "a8dc904fa12d6e2b2a3c02ad36f8816d" // Replace with your API Key
                    var searchId by remember { mutableStateOf("") }

                    Column(modifier = Modifier.padding(16.dp)) {
                        TextField(
                            value = searchId,
                            onValueChange = { searchId = it },
                            label = { Text("Masukkan superhero ID") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { viewModel.getSuperhero(apiKey, searchId) },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Cari")
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        // Access superhero directly from viewModel
                        viewModel.superhero.value?.let { superhero ->
                            SuperheroItem(superhero)
                        }
                    }
                }
            }
        }
    }

    fun parseSuperhero(jsonString: String): Superhero? {
        return try {
            Json.decodeFromString<Superhero>(jsonString)
        } catch (e: Exception) {
            null
        }
    }

    @Composable
    fun SuperheroItem(superhero: Superhero) {
        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = superhero.name ?: "Unknown", style = MaterialTheme.typography.h6)
                Image(
                    painter = rememberAsyncImagePainter(superhero.image?.url ?: ""),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(200.dp).fillMaxWidth()
                )
            }
        }
    }

}