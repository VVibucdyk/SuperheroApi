package viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import model.Superhero
import network.RetrofitInstance
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class SuperheroViewModel : ViewModel() {
    private val _superhero = mutableStateOf<Superhero?>(null)
    val superhero get() = _superhero

    fun getSuperhero(apiKey: String, id: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getSuperhero(apiKey, id)
                _superhero.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}