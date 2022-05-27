package com.example.data.research.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.research.room.entities.MainInfoTuples
import com.example.data.research.room.entities.ResearchDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ResearchDao {

    @Query("SELECT * FROM research WHERE id=:id")
    fun getById(id: Long): Flow<ResearchDbEntity?>

    @Query("SELECT id, date, region, district, settlement, name_reservoir, latitude_by_hand, longitude_by_hand FROM research")
    fun getAllMain(): Flow<List<MainInfoTuples>>

    @Query("SELECT id, date, region, district, settlement, name_reservoir, latitude_by_hand, longitude_by_hand FROM research WHERE id=:id ")
    suspend fun getMainById(id: Long): MainInfoTuples

    @Insert(entity = ResearchDbEntity::class)
    suspend fun insertResearch(researchDbEntity: ResearchDbEntity)

}