package com.example.data.hydrobionts.room

import androidx.room.Dao
import androidx.room.Query
import com.example.data.hydrobionts.room.entities.HydrobiontDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HydrobiontDao {

    @Query("SELECT * FROM hydrobionts WHERE id=:id")
    fun getById(id: Long): Flow<HydrobiontDbEntity>

    @Query("SELECT * FROM hydrobionts")
    fun getAll(): Flow<List<HydrobiontDbEntity>>
}