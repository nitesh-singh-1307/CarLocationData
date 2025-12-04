package com.example.codeassignment.domain.repository

import com.example.codeassignment.presentation.model.FilteredLocation
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun saveLocation(location: FilteredLocation)
    fun observeLocationHistory(): Flow<List<FilteredLocation>>

    suspend fun getLocationHistory(): List<FilteredLocation>

}