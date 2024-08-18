package com.lnight.externalstorageandroid15

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MediaListItem(
    modifier: Modifier = Modifier,
    file: MediaFile
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        when(file.type) {
            MediaType.IMAGE -> {
                AsyncImage(
                    model = file.uri,
                    contentDescription = null,
                    modifier = Modifier.width(100.dp)
                )
            }
            is MediaType.VIDEO -> {
                val thumbnail = file.type.thumbnail
                if (thumbnail != null) {
                    Image(
                        bitmap = thumbnail.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.width(100.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.video_icon),
                        contentDescription = null,
                        modifier = Modifier.width(100.dp)
                    )
                }
            }
            MediaType.AUDIO -> {
                Image(
                    painter = painterResource(id = R.drawable.audio_icon),
                    contentDescription = null,
                    modifier = Modifier.width(100.dp)
                )
            }
        }
        if(file.type is MediaType.VIDEO) {
            Text(
                text = "${file.name} - VIDEO",
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            )
        } else {
            Text(
                text = "${file.name} - ${file.type}",
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            )
        }
    }
}