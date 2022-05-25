package com.example.data.probe.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.data.hydrobionts.room.entities.HydrobiontDbEntity
import com.example.data.probe.entities.Probe
import com.example.data.research.room.entities.ResearchDbEntity

@Entity(
    tableName = "probe",
    primaryKeys = ["research_id", "hydrobiont_id"],
    foreignKeys = [
        ForeignKey(
            entity = ResearchDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["research_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = HydrobiontDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["hydrobiont_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class ProbeDbEntity(
    @ColumnInfo(name = "research_id") val researchId: Long,
    @ColumnInfo(name = "hydrobiont_id") val hydrobiontId: Long,
    val amount: Int,
    val percent: Double
) {
    fun toProbe(): Probe = Probe(
        researchId = researchId,
        hydrobiontId = hydrobiontId,
        amount = amount,
        percent = percent
    )
}