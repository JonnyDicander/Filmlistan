
package com.example.filmlistan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// Filmens dataklass
data class Film(
    val id: Int,
    val titel: String,
    val ar: Int,
    val genre: String,
    val sedd: Boolean = false,
)

// Appens UI-state
data class FilmlistanUiState(
    val filmer: List<Film> = listOf(
        Film(1, "Norrskensväktaren", 2024, "Äventyr"),
        Film(2, "Den sista bussen", 2021, "Drama"),
        Film(3, "Kodnamn Granit", 2023, "Thriller"),
    )
)

// ViewModel som äger filmlistan
class FilmlistanViewModel : ViewModel() {

    var uiState by mutableStateOf(FilmlistanUiState())
        private set

}
