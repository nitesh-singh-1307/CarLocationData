package com.example.codeassignment.domain.filter

import com.example.codeassignment.presentation.model.Coordinate
import com.example.codeassignment.presentation.model.FilteredLocation
import com.example.codeassignment.presentation.model.RawLocationSample

class SimpleSmoothingFilter : LocationFilterAlgorithm {

    override fun filter(raw: RawLocationSample): FilteredLocation {
        val rounded = Coordinate(
            latitude = "%.5f".format(raw.coordinate.latitude).toDouble(),
            longitude = "%.5f".format(raw.coordinate.longitude).toDouble()
        )
        return FilteredLocation(
            coordinate = rounded,
            timestampMillis = raw.timestampMillis
        )
    }

}