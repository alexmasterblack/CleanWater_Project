package com.example.data.image.room.entities

import androidx.room.ColumnInfo

data class ImagePathTuples(
    @ColumnInfo(name = "image_path") val imagePath: Int,
)