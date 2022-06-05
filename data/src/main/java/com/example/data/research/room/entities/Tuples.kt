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

data class UpdateResearchTuples(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "latitude_by_hand") val latitudeByHand: String,
    @ColumnInfo(name = "longitude_by_hand") val longitudeByHand: String,
    val region: String,
    val district: String,
    val settlement: String,
    @ColumnInfo(name = "name_reservoir") val nameReservoir: String,
    @ColumnInfo(name = "river_basin") val riverBasin: String,
    @ColumnInfo(name = "length_stream") val lengthStream: String,
    @ColumnInfo(name = "air_temperature") val airTemperature: Double,
    @ColumnInfo(name = "water_temperature") val waterTemperature: Double,
    val comment: String,
    @ColumnInfo(name = "part_river_basin") val partRiverBasin: String,
    @ColumnInfo(name = "longitudinal_zone") val longitudinalZone: String,
    @ColumnInfo(name = "longitudinal_element_riverbed") val longitudinalElementRiverbed: String,
    @ColumnInfo(name = "transverse_element_riverbed") val transverseElementRiverbed: String,
    @ColumnInfo(name = "current_velosity") val currentVelosity: Double,
    @ColumnInfo(name = "width_riverbed") val widthRiverbed: Double,
    @ColumnInfo(name = "average_depth") val averageDepth: Double,
    @ColumnInfo(name = "depth_sampling_point") val depthSamplingPoint: Double,
    @ColumnInfo(name = "bottom_substrate_type") val bottomSubstrateType: String,
    @ColumnInfo(name = "development_aquatic_vegetation") val developmentAquaticVegetation: String,
    @ColumnInfo(name = "type_riparian_vegetation") val typeRiparianVegetation: String,
    @ColumnInfo(name = "illumination_riverbed") val illuminationRiverbed: String,
    @ColumnInfo(name = "anthropogenic_impact") val anthropogenicImpact: String,
    @ColumnInfo(name = "sampling_method") val samplingMethod: String,
    @ColumnInfo(name = "type_sampler") val typeSampler: String
)