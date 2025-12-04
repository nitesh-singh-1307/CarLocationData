package com.example.codeassignment

import com.example.codeassignment.data.location.InMemoryLocationRepository
import com.example.codeassignment.data.poi.FakePoiRepository
import com.example.codeassignment.domain.filter.AggressiveSmoothingFilter
import com.example.codeassignment.domain.filter.LocationFilterRegistry
import com.example.codeassignment.domain.filter.SimpleSmoothingFilter
import com.example.codeassignment.domain.repository.LocationRepository
import com.example.codeassignment.domain.repository.PoiRepository
import com.example.codeassignment.domain.usecase.ProcessLocationUpdateUseCase
import com.example.codeassignment.presentation.viewmodel.LocationViewModel

class AppContainer {
    // Data layer
    private val locationRepository: LocationRepository = InMemoryLocationRepository()
    private val poiRepository: PoiRepository = FakePoiRepository()

    // Filter algorithms
    private val defaultFilter = SimpleSmoothingFilter()
    private val aggressiveFilter = AggressiveSmoothingFilter()

    // Hard-coded mapping: assume car model "MODEL_X" uses aggressive filter.
    private val filterRegistry = LocationFilterRegistry(
        defaultAlgorithm = defaultFilter,
        algorithmMap = mapOf(
            "MODEL_X" to aggressiveFilter
        )
    )

    // Use cases
    private val processLocationUpdateUseCase =
        ProcessLocationUpdateUseCase(locationRepository, poiRepository, filterRegistry)

    // ViewModels
    val locationViewModel: LocationViewModel =
        LocationViewModel(processLocationUpdateUseCase, locationRepository)
}