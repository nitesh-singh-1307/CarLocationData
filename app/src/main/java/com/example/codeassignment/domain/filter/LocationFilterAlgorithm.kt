package com.example.codeassignment.domain.filter

import com.example.codeassignment.presentation.model.FilteredLocation
import com.example.codeassignment.presentation.model.RawLocationSample

interface LocationFilterAlgorithm {
    fun filter(raw: RawLocationSample): FilteredLocation
}