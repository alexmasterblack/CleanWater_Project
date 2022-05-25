package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.hydrobionts.room.HydrobiontDao
import com.example.data.hydrobionts.room.entities.HydrobiontDbEntity
import com.example.data.image.room.ImageDao
import com.example.data.image.room.entities.ImageDbEntity
import com.example.data.index.room.IndexDao
import com.example.data.index.room.entities.IndexDbEntity
import com.example.data.indices.room.IndexNameDao
import com.example.data.indices.room.entities.IndexNameDbEntity
import com.example.data.probe.room.ProbeDao
import com.example.data.probe.room.entities.ProbeDbEntity
import com.example.data.research.room.ResearchDao
import com.example.data.research.room.entities.ResearchDbEntity

@Database(
    version = 1,
    entities = [
        ResearchDbEntity::class,
        HydrobiontDbEntity::class,
        ProbeDbEntity::class,
        IndexDbEntity::class,
        IndexNameDbEntity::class,
        ImageDbEntity::class
    ]
)
abstract class Database : RoomDatabase() {

//    abstract fun getResearchDao(): ResearchDao

    abstract fun getHydrobiontDao(): HydrobiontDao

//    abstract fun getProbeDao(): ProbeDao
//
//    abstract fun getIndex(): IndexDao
//
//    abstract fun getIndexName(): IndexNameDao
//
//    abstract fun getImage(): ImageDao
}