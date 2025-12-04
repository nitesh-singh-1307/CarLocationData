package com.example.codeassignment.presentation.model

data class Coordinate(
    val latitude: Double,
    val longitude: Double
)

enum class PoiType {
    RESTAURANT,
    CHARGING_STATION,
    OTHER
}

data class Poi(
    val id: String,
    val name: String,
    val type: PoiType,
    val location: Coordinate,
    val distanceMeters: Double
)

data class RawLocationSample(
    val coordinate: Coordinate,
    val speedMetersPerSecond: Double,
    val timestampMillis: Long
)
data class FilteredLocation(
    val coordinate: Coordinate,
    val timestampMillis: Long
)
data class CarModel(
    val id: String,
    val name: String
)