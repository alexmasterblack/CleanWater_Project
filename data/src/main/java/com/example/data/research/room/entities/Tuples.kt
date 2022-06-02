package com.example.data.research.room.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class MainInfoTuples(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "collection_number") val collectionNumber: Long,
    val date: String,
    val region: String,
    val district: String,
    val settlement: String,
    @ColumnInfo(name = "name_reservoir") val nameReservoir: String,
    @ColumnInfo(name = "latitude_by_hand") val latitudeByHand: String,
    @ColumnInfo(name = "longitude_by_hand") val longitudeByHand: String,
)