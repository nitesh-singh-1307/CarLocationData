package com.example.codeassignment.data.poi

import com.example.codeassignment.domain.repository.PoiRepository
import com.example.codeassignment.presentation.model.Coordinate
import com.example.codeassignment.presentation.model.Poi
import com.example.codeassignment.presentation.model.PoiType
import kotlin.math.abs

class FakePoiRepository: PoiRepository {
    override suspend fun getPoisNear(
        coordinate: Coordinate,
        types: Set<PoiType>
    ): List<Poi> {

        val allPois = listOf(
            Poi(
                id = "1",
                name = "Sample Restaurant",
                type = PoiType.RESTAURANT,
                location = coordinate,
                distanceMeters = 150.0
            ),
            Poi(
                id = "2",
                name = "Sample Charging Station",
                type = PoiType.CHARGING_STATION,
                location = coordinate.copy(
                    latitude = coordinate.latitude + 0.001
                ),
                distanceMeters = 250.0
            )
        )
        return allPois.filter { it.type in types }
            .sortedBy { abs(it.distanceMeters) }
    }
}