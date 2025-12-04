package com.example.codeassignment.domain.model

import com.example.codeassignment.presentation.model.FilteredLocation
import com.example.codeassignment.presentation.model.Poi

data class LocationUiState(
    val isLoading: Boolean = false,
    val currentLocation: FilteredLocation? = null,
    val currentPois: List<Poi> = emptyList(),
    val locationHistory: List<FilteredLocation> = emptyList(),
    val errorMessage: String? = null
)
