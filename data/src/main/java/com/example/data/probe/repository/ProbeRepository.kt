package com.example.data.probe.repository

import com.example.data.probe.entities.Probe
import kotlinx.coroutines.flow.Flow

interface ProbeRepository {

    suspend fun addProbe(probe: Probe)

    suspend fun getAllProbe(researchId: Long): Flow<List<Probe>>

    suspend fun getTotalAmount(researchId: Long): Flow<Int?>

    suspend fun getIndexEPT(researchId: Long): Flow<Double?>

    suspend fun updateProbeAmount(probe: Probe)
}