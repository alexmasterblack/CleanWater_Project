package com.example.data.hydrobionts.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.hydrobionts.entities.Hydrobiont

@Entity(
    tableName = "hydrobionts",
    indices = [
        Index("latin_name", unique = true)
    ]
)
data class HydrobiontDbEntity(
    @PrimaryKey val id: Long,
    val name: String,
    @ColumnInfo(name = "latin_name") val latinName: String,
    val description: String
) {
    fun toHydrobiont(): Hydrobiont = Hydrobiont(
        id = id,
        name = name,
        latinName = latinName,
        description = description
    )
}
