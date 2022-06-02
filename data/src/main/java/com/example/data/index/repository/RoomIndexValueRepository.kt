package com.example.data.index.repository

import com.example.data.index.entities.IndexValue
import com.example.data.index.room.IndexValueDao
import com.example.data.index.room.entities.IndexValueDbEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomIndexValueRepository(private val indexValueDao: IndexValueDao) : IndexValueRepository {

    override suspend fun addIndexValue(indexValue: IndexValue) {
        insertIndex(indexValue)
    }

    override suspend fun getEPTIndex(researchId: Long, indexNameId: Long): Flow<IndexValue> {
        return getEPTIndexById(researchId, indexNameId)
    }

    private fun getAllIndicesByResearchId(researchId: Long): Flow<List<IndexValue?>> {
        return indexValueDao.getAllByResearchId(researchId)
            .map { it.map { indexDbEntity -> indexDbEntity?.toIndexValue() } }
    }

    private fun getEPTIndexById(researchId: Long, indexNameId: Long): Flow<IndexValue> {
        return indexValueDao.getEPTIndexById(researchId, indexNameId).map { it.toIndexValue() }
    }

    private suspend fun insertIndex(indexValue: IndexValue) {
        val entity = IndexValueDbEntity.fromIndex(indexValue)
        indexValueDao.insertIndex(entity)
    }

    private suspend fun updateIndex(indexValue: IndexValue) {
        indexValueDao.updateIndex(
            IndexValueDbEntity(
                indexValue.researchId,
                indexValue.indexNameId,
                indexValue.value,
                indexValue.waterQuality
            )
        )
    }
}