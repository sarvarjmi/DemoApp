package com.demoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demoapp.domain.usecase.GetHomeFeedUseCase
import com.demoapp.presentation.state.HomeFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFeedViewModel @Inject constructor(
    private val getHomeFeedUseCase: GetHomeFeedUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeFeedUiState())
    val uiState: StateFlow<HomeFeedUiState> = _uiState.asStateFlow()

    init {
        observeHomeFeed()
        refreshFeed()
    }

    private fun observeHomeFeed() {
        viewModelScope.launch {
            getHomeFeedUseCase()
                .onStart { _uiState.value = _uiState.value.copy(isLoading = true) }
                .catch { e ->
                    _uiState.value = _uiState.value.copy(
                        error = e.message ?: "An unknown error occurred",
                        isLoading = false
                    )
                }
                .collect { feed ->
                    _uiState.value = _uiState.value.copy(
                        feed = feed,
                        isLoading = false,
                        error = null
                    )
                }
        }
    }

    fun refreshFeed() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                getHomeFeedUseCase.refresh()
                // Note: isLoading will be set to false by observeHomeFeed when DB updates
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "An unknown error occurred",
                    isLoading = false
                )
            }
        }
    }
}
