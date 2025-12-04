package com.example.codeassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeassignment.domain.model.LocationUiState
import com.example.codeassignment.domain.repository.LocationRepository
import com.example.codeassignment.domain.usecase.ProcessLocationUpdateUseCase
import com.example.codeassignment.presentation.model.CarModel
import com.example.codeassignment.presentation.model.PoiType
import com.example.codeassignment.presentation.model.RawLocationSample
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationViewModel(
    private val processLocationUpdateUseCase: ProcessLocationUpdateUseCase,
    private val locationRepository: LocationRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(LocationUiState())
    val uiState: StateFlow<LocationUiState> = _uiState.asStateFlow()

    fun onLocationUpdate(
        carModel: CarModel,
        rawSample: RawLocationSample,
        types: Set<PoiType>
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                val result = processLocationUpdateUseCase(
                    carModel = carModel,
                    rawLocationSample = rawSample,
                    types = types
                )

                val history = locationRepository.getLocationHistory()

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    currentLocation = result.filteredLocation,
                    currentPois = result.pois,
                    locationHistory = history,
                    errorMessage = null
                )
            } catch (t: Throwable) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = t.message ?: "Unknown error"
                )
            }
        }
    }

}