package com.demoapp.presentation.state

import com.demoapp.domain.model.HomeModule

data class HomeFeedUiState(
    val isLoading: Boolean = false,
    val feed: List<HomeModule> = emptyList(),
    val error: String? = null
)
