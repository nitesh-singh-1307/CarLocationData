package com.example.codeassignment.domain.repository

import com.example.codeassignment.presentation.model.Coordinate
import com.example.codeassignment.presentation.model.Poi
import com.example.codeassignment.presentation.model.PoiType

interface PoiRepository {
    suspend fun getPoisNear(
        coordinate: Coordinate,
        types: Set<PoiType>
    ): List<Poi>
}