package com.example.data.indices.repository

import com.example.data.indices.entities.IndexName
import com.example.data.indices.room.IndexNameDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomIndexNameRepository(private val indexNameDao: IndexNameDao) : IndexNameRepository {

    private fun getNameIndexById(id: Long): Flow<IndexName?> {
        return indexNameDao.getById(id).map { it?.toIndexName() }
    }
}