package com.example.data.probe.repository

import com.example.data.probe.entities.Probe
import com.example.data.probe.room.ProbeDao
import com.example.data.probe.room.entities.ProbeDbEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomProbeRepository(private val probeDao: ProbeDao) : ProbeRepository {

    override suspend fun addProbe(probe: Probe) {
        insertProbe(probe)
    }

    override suspend fun getAllProbe(researchId: Long): Flow<List<Probe>> {
        return getAllProbeByResearchId(researchId)
    }

    override suspend fun getTotalAmount(researchId: Long): Flow<Int?> {
        return getCountAmount(researchId)
    }

    override suspend fun getIndexEPT(researchId: Long): Flow<Double?> {
        return getIndexEPTByResearchId(researchId)
    }

    override suspend fun updateProbeAmount(probe: Probe) {
        updateProbe(probe)
    }

    private fun getAllProbeByResearchId(researchId: Long): Flow<List<Probe>> {
        return probeDao.getAllByResearchId(researchId)
            .map { it.map { probeDbEntity -> probeDbEntity.toProbe() } }
    }

    private fun getIndexEPTByResearchId(researchId: Long): Flow<Double?> {
        return probeDao.getIndexEPTByResearchId(researchId)
    }

    private suspend fun updateProbe(probe: Probe) {
        probeDao.updateProbe(
            ProbeDbEntity(
                probe.researchId,
                probe.hydrobiontId,
                probe.amount,
                probe.percent
            )
        )
    }

    private fun getCountAmount(researchId: Long): Flow<Int?> {
        return probeDao.getCountAmount(researchId)
    }

    private suspend fun insertProbe(probe: Probe) {
        val entity = ProbeDbEntity.fromProbe(probe)
        probeDao.insertProbe(entity)
    }
}