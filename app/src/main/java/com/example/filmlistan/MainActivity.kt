
package com.example.filmlistan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.filmlistan.ui.theme.FilmlistanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FilmlistanTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    FilmSkarm(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun FilmSkarm(
    modifier: Modifier = Modifier,
    viewModel: FilmlistanViewModel = viewModel()
) {
    // Hämta information från ViewModel
    val uiState = viewModel.uiState

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Topplist
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF315B87))
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Min filmlista",
                color = Color.White,
                fontSize = 24.sp
            )
        }

        // Lista med filmer från UiState
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = uiState.filmer,
                key = { film -> film.id }
            ) { film ->
                FilmKort(film = film)
            }
        }
    }
}

@Composable
fun FilmKort(film: Film) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(85.dp)
                    .background(
                        color = Color(0xFF527A9D),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = film.titel.first().toString(),
                    color = Color.White,
                    fontSize = 32.sp
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Column {
                Text(
                    text = film.titel,
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(text = "År: ${film.ar}")
                Text(text = "Genre: ${film.genre}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilmSkarmPreview() {
    FilmlistanTheme {
        FilmSkarm()
    }
}
