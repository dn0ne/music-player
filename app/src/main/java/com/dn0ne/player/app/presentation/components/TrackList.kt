package com.dn0ne.player.app.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dn0ne.player.app.domain.track.Track

fun LazyListScope.trackList(
    trackList: List<Track>,
    currentTrack: Track?,
    onTrackClick: (Track) -> Unit,
    onPlayNextClick: (Track) -> Unit,
    onAddToQueueClick: (Track) -> Unit,
    onAddToPlaylistClick: (Track) -> Unit,
    onViewTrackInfoClick: (Track) -> Unit,
) {
    items(
        items = trackList,
        key = { it.uri }
    ) { track ->
        TrackListItem(
            track = track,
            isCurrent = currentTrack == track,
            onClick = { onTrackClick(track) },
            onPlayNextClick = { onPlayNextClick(track) },
            onAddToQueueClick = { onAddToQueueClick(track) },
            onAddToPlaylistClick = { onAddToPlaylistClick(track) },
            onViewTrackInfoClick = { onViewTrackInfoClick(track) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .animateItem()
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}

fun LazyGridScope.trackList(
    trackList: List<Track>,
    currentTrack: Track?,
    onTrackClick: (Track) -> Unit,
    onPlayNextClick: (Track) -> Unit,
    onAddToQueueClick: (Track) -> Unit,
    onAddToPlaylistClick: (Track) -> Unit,
    onViewTrackInfoClick: (Track) -> Unit,
) {
    items(
        items = trackList,
        key = { it.uri }
    ) { track ->
        TrackListItem(
            track = track,
            isCurrent = currentTrack == track,
            onClick = { onTrackClick(track) },
            onPlayNextClick = { onPlayNextClick(track) },
            onAddToQueueClick = { onAddToQueueClick(track) },
            onAddToPlaylistClick = { onAddToPlaylistClick(track) },
            onViewTrackInfoClick = { onViewTrackInfoClick(track) },
            modifier = Modifier
                .fillMaxWidth()
                .animateItem()
        )

        //Spacer(modifier = Modifier.height(8.dp))
    }
}