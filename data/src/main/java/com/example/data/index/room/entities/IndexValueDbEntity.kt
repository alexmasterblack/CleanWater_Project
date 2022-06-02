package com.example.data.index.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.data.index.entities.IndexValue
import com.example.data.indices.room.entities.IndexNameDbEntity
import com.example.data.research.room.entities.ResearchDbEntity

@Entity(
    tableName = "index_value",
    primaryKeys = ["research_id", "index_name_id"],
    foreignKeys = [
        ForeignKey(
            entity = ResearchDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["research_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = IndexNameDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["index_name_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class IndexValueDbEntity(
    @ColumnInfo(name = "research_id") val researchId: Long,
    @ColumnInfo(name = "index_name_id") val indexNameId: Long,
    val value: Double,
    @ColumnInfo(name = "water_quality") val waterQuality: String
) {

    fun toIndexValue(): IndexValue = IndexValue(
        researchId = researchId,
        indexNameId = indexNameId,
        value = value,
        waterQuality = waterQuality
    )

    companion object {
        fun fromIndex(indexValue: IndexValue) = IndexValueDbEntity(
            researchId = indexValue.researchId,
            indexNameId = indexValue.indexNameId,
            value = indexValue.value,
            waterQuality = indexValue.waterQuality
        )
    }
}