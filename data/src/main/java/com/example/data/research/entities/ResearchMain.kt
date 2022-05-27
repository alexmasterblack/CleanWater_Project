package com.example.data.research.entities

data class ResearchMain(
    val id: Long,
    val date: String,
    val region: String,
    val district: String,
    val settlement: String,
    val nameReservoir: String,
    val latitudeByHand: String,
    val longitudeByHand: String,
)