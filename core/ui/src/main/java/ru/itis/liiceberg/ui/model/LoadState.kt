package ru.itis.liiceberg.ui.model

sealed interface LoadState {
    data object Initial: LoadState
    data object Loading: LoadState
    data object Success: LoadState
    data class Error(val message: String): LoadState
}