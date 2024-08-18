package com.lnight.externalstorageandroid15

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.ContextCompat

class MediaReader(
    private val context: Context
) {
    fun getAllMediaFiles(): List<MediaFile> {
        // Not having permission on < 33 makes the app crash
        // when attempting to query
        val skipQuery = if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        } else false

        if(skipQuery) {
            return emptyList()
        }

        val mediaFiles = mutableListOf<MediaFile>()

        val queryUri = if(Build.VERSION.SDK_INT >= 29) {
            MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else MediaStore.Files.getContentUri("external")

        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.MIME_TYPE,
        )

        context.contentResolver.query(
            queryUri,
            projection,
            null,
            null,
            null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(
                MediaStore.Files.FileColumns._ID
            )
            val nameColumn = cursor.getColumnIndexOrThrow(
                MediaStore.Files.FileColumns.DISPLAY_NAME
            )
            val mimeTypeColumn = cursor.getColumnIndexOrThrow(
                MediaStore.Files.FileColumns.MIME_TYPE
            )

            while(cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val mimeType = cursor.getString(mimeTypeColumn)

                if(name != null && mimeType != null) {
                    val contentUri = ContentUris.withAppendedId(
                        queryUri,
                        id
                    )
                    val mediaType = when {
                        mimeType.startsWith("audio/") -> MediaType.AUDIO
                        mimeType.startsWith("video/") -> {
                            val thumbnail = getVideoThumbnail(contentUri)
                            MediaType.VIDEO(thumbnail)
                        }
                        else -> MediaType.IMAGE
                    }

                    mediaFiles.add(
                        MediaFile(
                            uri = contentUri,
                            name = name,
                            type = mediaType
                        )
                    )
                }
            }
        }

        return mediaFiles.toList()
    }

    private fun getVideoThumbnail(uri: Uri): Bitmap? {
        val retriever = MediaMetadataRetriever()
        return try {
            retriever.setDataSource(context, uri)
            retriever.getFrameAtTime(1000000) // Get a frame at the 1-second mark
        } catch (e: Exception) {
            null
        } finally {
            retriever.release()
        }
    }
}