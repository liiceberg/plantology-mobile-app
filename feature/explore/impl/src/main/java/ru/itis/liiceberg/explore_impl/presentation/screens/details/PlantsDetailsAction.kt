package ru.itis.liiceberg.explore_impl.presentation.screens.details

import ru.itis.liiceberg.ui.model.UiAction

sealed class PlantsDetailsAction: UiAction {
    data object ShowSuccessAddToFavoriteMessage : PlantsDetailsAction()
}