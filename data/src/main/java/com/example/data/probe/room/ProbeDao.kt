package com.example.data.probe.room

import androidx.room.*
import com.example.data.probe.room.entities.ProbeDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProbeDao {

    @Query("SELECT * FROM probe WHERE research_id=:researchId")
    fun getAllByResearchId(researchId: Long): Flow<List<ProbeDbEntity>>

    @Query("SELECT SUM(amount) FROM probe WHERE research_id=:researchId")
    fun getCountAmount(researchId: Long): Flow<Int?>

    @Query("SELECT SUM(percent) FROM probe WHERE research_id=:researchId AND hydrobiont_id <= 3")
    fun getIndexEPTByResearchId(researchId: Long): Flow<Double?>

    @Update(entity = ProbeDbEntity::class)
    suspend fun updateProbe(probeDbEntity: ProbeDbEntity)

    @Insert(entity = ProbeDbEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProbe(probeDbEntity: ProbeDbEntity)
}