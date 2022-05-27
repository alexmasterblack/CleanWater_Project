package com.example.data.repository

import android.content.Context
import androidx.room.Room
import com.example.data.hydrobionts.room.repository.HydrobiontRepository
import com.example.data.hydrobionts.room.repository.RoomHydrobiontRepository
import com.example.data.image.room.repository.ImageRepository
import com.example.data.image.room.repository.RoomImageRepository
import com.example.data.index.repository.IndexValueRepository
import com.example.data.index.repository.RoomIndexValueRepository
import com.example.data.indices.repository.IndexNameRepository
import com.example.data.indices.repository.RoomIndexNameRepository
import com.example.data.research.repository.ResearchRepository
import com.example.data.research.repository.RoomResearchRepository
import com.example.data.room.AppDatabase

object Repositories {

    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context
    }

    private val database: AppDatabase by lazy<AppDatabase> {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
            .createFromAsset("initial_database.db").build()
    }

    val hydrobiontRepository: HydrobiontRepository by lazy {
        RoomHydrobiontRepository(database.getHydrobiontDao())
    }

    val imageRepository: ImageRepository by lazy {
        RoomImageRepository(database.getImageDao())
    }

    val indexNameRepository: IndexNameRepository by lazy {
        RoomIndexNameRepository(database.getIndexNameDao())
    }

    val researchRepository: ResearchRepository by lazy {
        RoomResearchRepository(database.getResearchDao())
    }

    val indexValueRepository: IndexValueRepository by lazy {
        RoomIndexValueRepository(database.getIndexValueDao())
    }
}