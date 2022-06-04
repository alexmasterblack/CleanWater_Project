package com.example.data.index.repository

import com.example.data.index.entities.IndexValue
import kotlinx.coroutines.flow.Flow

interface IndexValueRepository {

    suspend fun addIndexValue(indexValue: IndexValue)

    suspend fun updateIndexValue(indexValue: IndexValue)

    suspend fun checkExists(researchId: Long) : Flow<Int>

    suspend fun getEPTIndex(researchId: Long, indexNameId: Long): Flow<IndexValue>
}