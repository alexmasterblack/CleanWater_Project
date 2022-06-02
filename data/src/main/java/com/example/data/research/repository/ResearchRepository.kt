package com.example.data.research.repository

import com.example.data.research.entities.Research
import com.example.data.research.entities.ResearchMain
import kotlinx.coroutines.flow.Flow

interface ResearchRepository {

    suspend fun getResearch(id: Long)

    suspend fun getMainInfo(id: Long): Flow<ResearchMain?>

    suspend fun getLastResearchId(): Flow<Long>

    suspend fun addResearch(research: Research)

    suspend fun getCardsResearch(): Flow<List<ResearchMain>>

    suspend fun deleteResearch(id: Long)

    suspend fun deleteLastResearch()
}