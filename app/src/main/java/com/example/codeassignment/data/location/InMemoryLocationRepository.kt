package com.example.codeassignment.data.location

import com.example.codeassignment.domain.repository.LocationRepository
import com.example.codeassignment.presentation.model.FilteredLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InMemoryLocationRepository : LocationRepository {
    private val history = mutableListOf<FilteredLocation>()
    private val historyFlow = MutableStateFlow<List<FilteredLocation>>(emptyList())

    override suspend fun saveLocation(location: FilteredLocation) {
        history.add(location)
        historyFlow.value = history.toList()
    }

    override fun observeLocationHistory(): Flow<List<FilteredLocation>>  = historyFlow.asStateFlow()


    override suspend fun getLocationHistory(): List<FilteredLocation> =  history.toList()

}