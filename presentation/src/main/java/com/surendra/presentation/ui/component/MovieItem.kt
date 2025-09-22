package com.surendra.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.surendra.domain.model.Movie

@Composable
fun MovieItem(
    movie: Movie,
    onMovieClick: (Movie) -> Unit,
    onBookmarkClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onMovieClick(movie) }
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Poster Image (circular)
        AsyncImage(
            model = movie.poster,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )

        // Title + Release Date + Rating
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
            Text(text = "Release: ${movie.releaseDate}", style = MaterialTheme.typography.bodySmall)
            Text(text = "⭐ ${movie.rating}", style = MaterialTheme.typography.bodySmall)
        }

        // Bookmark button
        IconButton(onClick = { onBookmarkClick(movie) }) {
            Icon(
                imageVector = Icons.Default.Bookmark,
                contentDescription = "Bookmark"
            )
        }
    }
}
