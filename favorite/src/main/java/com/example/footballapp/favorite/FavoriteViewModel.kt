package com.example.footballapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.footballapp.core.domain.usecase.FootballUseCase

class FavoriteViewModel(footballUseCase: FootballUseCase) : ViewModel() {
    val favorites = footballUseCase.getFavoritesTeam().asLiveData()
}