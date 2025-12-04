package com.example.codeassignment.domain.filter

import com.example.codeassignment.presentation.model.FilteredLocation
import com.example.codeassignment.presentation.model.RawLocationSample

class AggressiveSmoothingFilter: LocationFilterAlgorithm  {
    private val delegate = SimpleSmoothingFilter()

    override fun filter(raw: RawLocationSample): FilteredLocation {
        return delegate.filter(raw)
    }
}