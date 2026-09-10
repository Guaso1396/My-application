package com.example.myapplication

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.ui.theme.MyApplicationTheme

private fun statusColor(status: String): Color = when (status.lowercase()) {
    "alive" -> Color(0xFF4CD964)
    "dead" -> Color(0xFFFF3B30)
    else -> Color(0xFFB0B0B0)
}

@Composable
fun CharacterItemView(character: Character, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column {
            // Imagen del personaje
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            Column(modifier = Modifier.padding(12.dp)) {
                // Nombre
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                // 🟢 Estado · Especie
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Canvas(modifier = Modifier.size(10.dp)) {
                        drawCircle(color = statusColor(character.status))
                    }
                    Text(
                        text = " ${character.status} · ${character.species}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // 🚻 Género
                Text(
                    text = "🚻 ${character.gender}",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )

                // 🌍 Origen
                Text(
                    text = "🌍 ${character.origin.name}",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )

                // 📍 Ubicación
                Text(
                    text = "📍 ${character.location.name}",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )

                // 🎬 Cantidad de episodios
                Text(
                    text = "🎬 ${character.episode.size} episodios",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterItemPreview() {
    MyApplicationTheme {
        CharacterItemView(
            character = Character(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                gender = "Male",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                origin = Origin("Earth (C-137)"),
                location = Origin("Citadel of Ricks"),
                episode = List(51) { "" }
            )
        )
    }
}