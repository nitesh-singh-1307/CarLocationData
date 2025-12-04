package com.example.codeassignment.domain.usecase

import com.example.codeassignment.domain.filter.LocationFilterRegistry
import com.example.codeassignment.domain.repository.LocationRepository
import com.example.codeassignment.domain.repository.PoiRepository
import com.example.codeassignment.presentation.model.CarModel
import com.example.codeassignment.presentation.model.FilteredLocation
import com.example.codeassignment.presentation.model.Poi
import com.example.codeassignment.presentation.model.PoiType
import com.example.codeassignment.presentation.model.RawLocationSample

class ProcessLocationUpdateUseCase(
    private val locationRepository: LocationRepository,
    private val poiRepository: PoiRepository,
    private val filterRegistry: LocationFilterRegistry
) {

    data class Result(
        val filteredLocation: FilteredLocation,
        val pois: List<Poi>
    )

    suspend operator fun invoke(
        carModel: CarModel,
        rawLocationSample: RawLocationSample,
        types: Set<PoiType>
    ): Result {
        val algorithm = filterRegistry.getAlgorithmFor(carModel)
        val filtered = algorithm.filter(rawLocationSample)

        locationRepository.saveLocation(filtered)

        val pois = poiRepository.getPoisNear(
            coordinate = filtered.coordinate,
            types = types
        )

        return Result(filteredLocation = filtered, pois = pois)
    }

}