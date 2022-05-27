package com.example.data.probe.repository

import com.example.data.probe.entities.Probe
import com.example.data.probe.room.ProbeDao
import com.example.data.probe.room.entities.ProbeDbEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomProbeRepository(private val probeDao: ProbeDao) : ProbeRepository {

    private fun getAllProbeByResearchId(researchId: Long): Flow<List<Probe?>> {
        return probeDao.getAllByResearchId(researchId)
            .map { it.map { probeDbEntity -> probeDbEntity?.toProbe() } }
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

    private suspend fun insertProbe(probe: Probe) {
        val entity = ProbeDbEntity.fromProbe(probe)
        probeDao.insertProbe(entity)
    }
}