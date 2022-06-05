package com.example.data.research.room

import androidx.room.*
import com.example.data.research.room.entities.MainInfoTuples
import com.example.data.research.room.entities.ResearchDbEntity
import com.example.data.research.room.entities.UpdateResearchTuples
import kotlinx.coroutines.flow.Flow

@Dao
interface ResearchDao {

    @Query("SELECT * FROM research WHERE id=:id")
    fun getById(id: Long): Flow<ResearchDbEntity>

    @Query("SELECT id, collection_number, date, region, district, settlement, name_reservoir, latitude_by_hand, longitude_by_hand FROM research")
    fun getAllMain(): Flow<List<MainInfoTuples>>

    @Query("SELECT id, collection_number, date, region, district, settlement, name_reservoir, latitude_by_hand, longitude_by_hand FROM research WHERE id=:id ")
    fun getMainById(id: Long): Flow<MainInfoTuples?>

    @Insert(entity = ResearchDbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResearch(researchDbEntity: ResearchDbEntity)

    @Update(entity = ResearchDbEntity::class)
    suspend fun updateResearchByResearch(updateResearchTuples: UpdateResearchTuples)

    @Query("DELETE FROM research WHERE id=:id")
    suspend fun deleteResearchById(id: Long)

    @Query("DELETE FROM research WHERE id=(SELECT MAX(id) FROM research)")
    suspend fun deleteLast()

    @Query("SELECT id FROM research WHERE id=(SELECT MAX(id) FROM research)")
    fun getLastId(): Flow<Long>
}