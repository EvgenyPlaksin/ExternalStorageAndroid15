package com.lnight.externalstorageandroid15

import android.graphics.Bitmap

sealed interface MediaType {
    data object IMAGE : MediaType
    data object AUDIO : MediaType
    class VIDEO(val thumbnail: Bitmap?) : MediaType
}