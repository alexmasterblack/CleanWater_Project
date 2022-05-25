package com.example.data.hydrobionts.repository

import android.database.sqlite.SQLiteConstraintException
import com.example.data.hydrobionts.entities.Hydrobiont
import com.example.data.hydrobionts.room.HydrobiontDao
import com.example.data.hydrobionts.room.entities.HydrobiontDbEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomHydrobiontRepository(private val hydrobiontDao: HydrobiontDao) {

    private fun getHydrobiontById(id: Long): Flow<Hydrobiont?> {
        return hydrobiontDao.getById(id)
            .map { hydrobiontDbEntity -> hydrobiontDbEntity?.toHydrobiont() }
    }

//    private suspend fun addHydrobiont(hydrobiont: Hydrobiont) {
//        try {
//            val entity = HydrobiontDbEntity.fromHydrobiont(hydrobiont)
//            hydrobiontDao.addHydrobiont(entity)
//        } catch (e: SQLiteConstraintException) {
//            // ошибка, если уже существует с таким латинским именем
//        }
//    }

}