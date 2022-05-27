package com.example.data.research.repository

import com.example.data.research.entities.Research
import com.example.data.research.entities.ResearchMain
import com.example.data.research.room.ResearchDao
import com.example.data.research.room.entities.ResearchDbEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomResearchRepository(private val researchDao: ResearchDao) : ResearchRepository {

    private fun getResearchById(id: Long): Flow<Research?> {
        return researchDao.getById(id).map { it?.toResearch() }
    }

    private fun getAllMainResearch(): Flow<List<ResearchMain>> {
        val tuple = researchDao.getAllMain()
        return tuple.map {
            it.map { mainInfoTuples ->
                ResearchMain(
                    mainInfoTuples.id,
                    mainInfoTuples.date,
                    mainInfoTuples.region,
                    mainInfoTuples.district,
                    mainInfoTuples.settlement,
                    mainInfoTuples.nameReservoir,
                    mainInfoTuples.latitudeByHand,
                    mainInfoTuples.longitudeByHand
                )
            }
        }
    }

    private suspend fun getMainResearchById(id: Long): ResearchMain {
        val tuple = researchDao.getMainById(id)
        return ResearchMain(
            tuple.id,
            tuple.date,
            tuple.region,
            tuple.district,
            tuple.settlement,
            tuple.nameReservoir,
            tuple.latitudeByHand,
            tuple.longitudeByHand
        )
    }

    private suspend fun insertResearch(research: Research) {
        val entity = ResearchDbEntity.fromResearch(research)
        researchDao.insertResearch(entity)
    }
}