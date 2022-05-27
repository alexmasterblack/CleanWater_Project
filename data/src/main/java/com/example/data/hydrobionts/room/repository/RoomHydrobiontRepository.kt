package com.example.data.hydrobionts.room.repository

import com.example.data.hydrobionts.entities.Hydrobiont
import com.example.data.hydrobionts.room.HydrobiontDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomHydrobiontRepository(private val hydrobiontDao: HydrobiontDao) : HydrobiontRepository {

    private fun getHydrobiontById(id: Long): Flow<Hydrobiont?> {
        return hydrobiontDao.getById(id)
            .map { it?.toHydrobiont() }
    }

    private fun getAllHydrobiont(): Flow<List<Hydrobiont?>> {
        return hydrobiontDao.getAll()
            .map { it.map { hydrobiontDbEntity -> hydrobiontDbEntity?.toHydrobiont() } }
    }

}