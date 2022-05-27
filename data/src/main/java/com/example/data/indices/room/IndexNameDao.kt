package com.example.data.indices.room

import androidx.room.Dao
import androidx.room.Query
import com.example.data.indices.room.entities.IndexNameDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IndexNameDao {

    @Query("SELECT * FROM index_name WHERE id=:id")
    fun getById(id: Long): Flow<IndexNameDbEntity?>
}