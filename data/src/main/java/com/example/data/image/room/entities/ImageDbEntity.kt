package com.example.data.image.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.data.hydrobionts.room.entities.HydrobiontDbEntity
import com.example.data.image.entities.Image

@Entity(
    tableName = "image",
    foreignKeys = [
        ForeignKey(
            entity = HydrobiontDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["hydrobiont_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class ImageDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "hydrobiont_id") val hydrobiontId: Long,
    @ColumnInfo(name = "image_path") val imagePath: Int,
) {
    fun toImage(): Image = Image(
        id = id,
        hydrobiontId = hydrobiontId,
        imagePath = imagePath
    )
}