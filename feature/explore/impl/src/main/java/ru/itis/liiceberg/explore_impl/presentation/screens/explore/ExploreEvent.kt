package ru.itis.liiceberg.explore_impl.presentation.screens.explore

import ru.itis.liiceberg.ui.model.UiEvent

sealed class ExploreEvent : UiEvent {
    data class OnSearchFieldFilled(val input: String) : ExploreEvent()
    data object OnSearch : ExploreEvent()
}