package com.example.data.image.room.repository

import com.example.data.image.entities.Image
import com.example.data.image.room.ImageDao
import com.example.data.image.room.entities.ImageDbEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomImageRepository(private val imageDao: ImageDao) : ImageRepository {

    override suspend fun getImage(hydrobiontId: Long): Flow<List<Int>> {
        return getImagePathByHydrobiontId(hydrobiontId)
    }

    override suspend fun addImage(image: Image) {
        insertImage(image)
    }

    private fun getImagePathByHydrobiontId(hydrobiontId: Long): Flow<List<Int>> {
        val tuple = imageDao.getImageByHydrobiontId(hydrobiontId)
        return tuple.map { image -> image.map { it.imagePath } }
    }

    private suspend fun insertImage(image: Image) {
        val entity = ImageDbEntity.fromImage(image)
        imageDao.insertImage(entity)
    }
}