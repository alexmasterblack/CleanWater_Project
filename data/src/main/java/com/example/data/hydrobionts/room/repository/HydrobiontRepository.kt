package com.example.data.hydrobionts.room.repository

import com.example.data.hydrobionts.entities.Hydrobiont
import kotlinx.coroutines.flow.Flow

interface HydrobiontRepository {

    suspend fun getHydrobiont(id: Long): Flow<Hydrobiont>

    suspend fun getHydrobionts(): Flow<List<Hydrobiont>>
}