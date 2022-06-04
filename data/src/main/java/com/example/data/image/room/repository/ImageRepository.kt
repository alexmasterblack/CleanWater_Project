package com.example.data.image.room.repository

import com.example.data.image.entities.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun getImage(hydrobiontId: Long): Flow<List<Int>>

    suspend fun addImage(image: Image)
}