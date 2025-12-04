package com.example.codeassignment.presentation.ui

import com.example.codeassignment.domain.model.LocationUiState

interface LocationView {
    fun render(state: LocationUiState)
}