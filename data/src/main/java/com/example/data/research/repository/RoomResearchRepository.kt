package com.example.data.research.repository

import com.example.data.research.entities.Research
import com.example.data.research.entities.ResearchMain
import com.example.data.research.room.ResearchDao
import com.example.data.research.room.entities.ResearchDbEntity
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomResearchRepository(private val researchDao: ResearchDao) : ResearchRepository {

    override suspend fun getResearch(id: Long) {
        getResearchById(id)
    }

    override suspend fun getMainInfo(id: Long): Flow<ResearchMain?> {
        return getMainResearchById(id)
    }

    override suspend fun getLastResearchId(): Flow<Long> {
        return getLastId()
    }

    override suspend fun addResearch(research: Research): Long {
        return insertResearch(research)
    }

    override suspend fun getCardsResearch(): Flow<List<ResearchMain>> {
        return getAllMainResearch()
    }

    override suspend fun deleteResearch(id: Long) {
        deleteResearchById(id)
    }

    override suspend fun deleteLastResearch() {
        deleteLast()
    }

    private fun getResearchById(id: Long): Flow<Research?> {
        return researchDao.getById(id).map { it?.toResearch() }
    }

    private fun getAllMainResearch(): Flow<List<ResearchMain>> {
        val tuple = researchDao.getAllMain()
        return tuple.map {
            it.map { mainInfoTuples ->
                ResearchMain(
                    mainInfoTuples.id,
                    mainInfoTuples.collectionNumber,
                    mainInfoTuples.date,
                    mainInfoTuples.region,
                    mainInfoTuples.district,
                    mainInfoTuples.settlement,
                    mainInfoTuples.nameReservoir,
                    mainInfoTuples.latitudeByHand,
                    mainInfoTuples.longitudeByHand
                )
            }.reversed()
        }
    }

    private fun getMainResearchById(id: Long): Flow<ResearchMain?> {
        val tuple = researchDao.getMainById(id)
        return tuple.map {
            it?.let { it1 ->
                ResearchMain(
                    it1.id,
                    it.collectionNumber,
                    it.date,
                    it.region,
                    it.district,
                    it.settlement,
                    it.nameReservoir,
                    it.latitudeByHand,
                    it.longitudeByHand
                )
            }
        }
    }

    private suspend fun insertResearch(research: Research): Long {
        val entity = ResearchDbEntity.fromResearch(research)
        return researchDao.insertResearch(entity)
    }

    private suspend fun deleteResearchById(id: Long) {
        researchDao.deleteResearchById(id)
    }

    private suspend fun deleteLast() {
        researchDao.deleteLast()
    }

    private fun getLastId(): Flow<Long> {
        return researchDao.getLastId()
    }
}