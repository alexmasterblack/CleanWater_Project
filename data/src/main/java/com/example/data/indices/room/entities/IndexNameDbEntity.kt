package com.example.data.indices.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.indices.entities.IndexName

@Entity(
    tableName = "index_name"
)
data class IndexNameDbEntity(
    @PrimaryKey val id: Long,
    val name: String,
) {
    fun toIndexName(): IndexName = IndexName(
        id = id,
        name = name
    )
}