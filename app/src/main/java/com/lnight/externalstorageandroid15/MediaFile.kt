package com.lnight.externalstorageandroid15

import android.net.Uri

data class MediaFile(
    val uri: Uri,
    val name: String,
    val type: MediaType
)
