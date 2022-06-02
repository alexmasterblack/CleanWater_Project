package com.example.data.index.room

import androidx.room.*
import com.example.data.index.room.entities.IndexValueDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IndexValueDao {

    @Query("SELECT * FROM index_value WHERE research_id=:researchId")
    fun getAllByResearchId(researchId: Long): Flow<List<IndexValueDbEntity?>>

    @Insert(entity = IndexValueDbEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIndex(indexValueDbEntity: IndexValueDbEntity)

    @Update(entity = IndexValueDbEntity::class)
    suspend fun updateIndex(indexValueDbEntity: IndexValueDbEntity)
}