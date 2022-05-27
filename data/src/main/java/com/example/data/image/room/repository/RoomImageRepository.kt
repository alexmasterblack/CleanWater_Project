package com.example.data.image.room.repository

import com.example.data.image.entities.Image
import com.example.data.image.room.ImageDao
import com.example.data.image.room.entities.ImageDbEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomImageRepository(private val imageDao: ImageDao) : ImageRepository {

    private fun getImagePathByHydrobiontId(hydrobiontId: Long): Flow<List<Int?>> {
        val tuple = imageDao.getImagePathByHydrobiontId(hydrobiontId)
        return tuple.map {
            it.map { imagePathTuples -> imagePathTuples?.imagePath }
        }
    }

    private suspend fun addImage(image: Image) {
        val entity = ImageDbEntity.fromImage(image)
        imageDao.addImage(entity)
    }
}