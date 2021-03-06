package com.example.data.research.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.research.entities.Research
import com.example.data.research.entities.ResearchMain

@Entity(
    tableName = "research",
)
data class ResearchDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "collection_number") val collectionNumber: Long,
    val date: String,
    @ColumnInfo(name = "latitude_by_hand") val latitudeByHand: String,
    @ColumnInfo(name = "latitude_auto") val latitudeAuto: String,
    @ColumnInfo(name = "longitude_by_hand") val longitudeByHand: String,
    @ColumnInfo(name = "longitude_auto") val longitudeAuto: String,
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
) {

    fun toResearch(): Research = Research(
        id = id,
        collectionNumber = collectionNumber,
        date = date,
        latitudeByHand = latitudeByHand,
        latitudeAuto = latitudeAuto,
        longitudeByHand = longitudeByHand,
        longitudeAuto = longitudeAuto,
        region = region,
        district = district,
        settlement = settlement,
        nameReservoir = nameReservoir,
        riverBasin = riverBasin,
        lengthStream = lengthStream,
        airTemperature = airTemperature,
        waterTemperature = waterTemperature,
        comment = comment,
        partRiverBasin = partRiverBasin,
        longitudinalZone = longitudinalZone,
        longitudinalElementRiverbed = longitudinalElementRiverbed,
        transverseElementRiverbed = transverseElementRiverbed,
        currentVelosity = currentVelosity,
        widthRiverbed = widthRiverbed,
        averageDepth = averageDepth,
        depthSamplingPoint = depthSamplingPoint,
        bottomSubstrateType = bottomSubstrateType,
        developmentAquaticVegetation = developmentAquaticVegetation,
        typeRiparianVegetation = typeRiparianVegetation,
        illuminationRiverbed = illuminationRiverbed,
        anthropogenicImpact = anthropogenicImpact,
        samplingMethod = samplingMethod,
        typeSampler = typeSampler
    )

    companion object {
        fun fromResearch(research: Research) = ResearchDbEntity(
            id = 0,
            collectionNumber = research.collectionNumber,
            date = research.date,
            latitudeByHand = research.latitudeByHand,
            latitudeAuto = research.latitudeAuto,
            longitudeByHand = research.longitudeByHand,
            longitudeAuto = research.longitudeAuto,
            region = research.region,
            district = research.district,
            settlement = research.settlement,
            nameReservoir = research.nameReservoir,
            riverBasin = research.riverBasin,
            lengthStream = research.lengthStream,
            airTemperature = research.airTemperature,
            waterTemperature = research.waterTemperature,
            comment = research.comment,
            partRiverBasin = research.partRiverBasin,
            longitudinalZone = research.longitudinalZone,
            longitudinalElementRiverbed = research.longitudinalElementRiverbed,
            transverseElementRiverbed = research.transverseElementRiverbed,
            currentVelosity = research.currentVelosity,
            widthRiverbed = research.widthRiverbed,
            averageDepth = research.averageDepth,
            depthSamplingPoint = research.depthSamplingPoint,
            bottomSubstrateType = research.bottomSubstrateType,
            developmentAquaticVegetation = research.developmentAquaticVegetation,
            typeRiparianVegetation = research.typeRiparianVegetation,
            illuminationRiverbed = research.illuminationRiverbed,
            anthropogenicImpact = research.anthropogenicImpact,
            samplingMethod = research.samplingMethod,
            typeSampler = research.typeSampler
        )
    }
}