package com.example.data.image.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.image.room.entities.ImageDbEntity
import com.example.data.image.room.entities.ImagePathTuples
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Query("SELECT image_path FROM image WHERE hydrobiont_id=:hydrobiontId")
    fun getImagePathByHydrobiontId(hydrobiontId: Long): Flow<List<ImagePathTuples?>>

    @Insert(entity = ImageDbEntity::class)
    suspend fun addImage(imageDbEntity: ImageDbEntity)
}