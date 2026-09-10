package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

sealed interface UiState {
    data object Loading : UiState
    data class Success(val characters: List<Character>, val info: Info) : UiState
    data class Error(val message: String) : UiState
}

private val screenGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFF0F2027), Color(0xFF2C5364), Color(0xFF1B1B2F))
)

@Composable
fun MainView(
    modifier: Modifier = Modifier,
    state: UiState,
    onPageChange: (Int) -> Unit,
    onRetry: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(screenGradient)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Rick & Morty",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            when (state) {
                is UiState.Loading -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF4CD964))
                }

                is UiState.Error -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Error: ${state.message}",
                            color = Color.White,
                            modifier = Modifier.padding(24.dp),
                            textAlign = TextAlign.Center
                        )
                        Button(onClick = onRetry) {
                            Text("Reintentar")
                        }
                    }
                }

                is UiState.Success -> Column(modifier = Modifier.fillMaxSize()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(items = state.characters, key = { it.id }) { character ->
                            CharacterItemView(character)
                        }
                    }
                    PaginationControls(
                        page = extractPage(state.info),
                        pages = state.info.pages,
                        hasPrev = state.info.prev != null,
                        hasNext = state.info.next != null,
                        onPageChange = onPageChange
                    )
                }
            }
        }
    }
}

private fun extractPage(info: Info): Int {
    val nextPage = info.next?.let { Regex("page=(\\d+)").find(it)?.groupValues?.get(1)?.toIntOrNull() }
    val prevPage = info.prev?.let { Regex("page=(\\d+)").find(it)?.groupValues?.get(1)?.toIntOrNull() }
    return when {
        nextPage != null -> nextPage - 1
        prevPage != null -> prevPage + 1
        else -> 1
    }
}

@Composable
private fun PaginationControls(
    page: Int,
    pages: Int,
    hasPrev: Boolean,
    hasNext: Boolean,
    onPageChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { onPageChange(page - 1) }, enabled = hasPrev) {
            Text("Anterior")
        }
        Text("Página $page / $pages", color = Color.White)
        Button(onClick = { onPageChange(page + 1) }, enabled = hasNext) {
            Text("Siguiente")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview() {
    MyApplicationTheme {
        MainView(
            state = UiState.Success(
                characters = listOf(
                    Character(1, "Rick Sanchez", "Alive", "Human", "Male",
                        "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                        Origin("Earth"), Origin("Citadel of Ricks")),
                    Character(2, "Morty Smith", "Alive", "Human", "Male",
                        "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                        Origin("Earth"), Origin("Earth"))
                ),
                info = Info(826, 42, "page=2", null)
            ),
            onPageChange = {},
            onRetry = {}
        )
    }
}