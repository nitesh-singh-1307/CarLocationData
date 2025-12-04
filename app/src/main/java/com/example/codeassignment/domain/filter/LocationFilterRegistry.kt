package com.example.codeassignment.domain.filter

import com.example.codeassignment.presentation.model.CarModel

class LocationFilterRegistry(
    private val defaultAlgorithm: LocationFilterAlgorithm,
    private val algorithmMap: Map<String, LocationFilterAlgorithm>
) {

    fun getAlgorithmFor(carModel: CarModel): LocationFilterAlgorithm {
        return algorithmMap[carModel.id] ?: defaultAlgorithm
    }
}