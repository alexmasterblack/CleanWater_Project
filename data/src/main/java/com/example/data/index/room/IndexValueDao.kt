package com.example.data.index.room

import androidx.room.*
import com.example.data.index.room.entities.IndexValueDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IndexValueDao {

    @Query("SELECT * FROM index_value WHERE research_id=:researchId")
    fun getAllByResearchId(researchId: Long): Flow<List<IndexValueDbEntity?>>

    @Query("SELECT * FROM index_value WHERE research_id=:researchId AND index_name_id=:indexNameId")
    fun getEPTIndexById(researchId: Long, indexNameId: Long): Flow<IndexValueDbEntity>

    @Query("SELECT EXISTS(SELECT * FROM index_value WHERE research_id=:researchId)")
    suspend fun checkExistsByResearchId(researchId: Long): Boolean

    @Insert(entity = IndexValueDbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIndex(indexValueDbEntity: IndexValueDbEntity)

    @Update(entity = IndexValueDbEntity::class)
    suspend fun updateIndex(indexValueDbEntity: IndexValueDbEntity)
}