package com.example.data.probe.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.probe.room.entities.ProbeDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProbeDao {

    @Query("SELECT * FROM probe WHERE research_id=:researchId")
    fun getAllByResearchId(researchId: Long): Flow<List<ProbeDbEntity?>>

    @Update(entity = ProbeDbEntity::class)
    suspend fun updateProbe(probeDbEntity: ProbeDbEntity)

    @Insert(entity = ProbeDbEntity::class)
    suspend fun insertProbe(probeDbEntity: ProbeDbEntity)
}