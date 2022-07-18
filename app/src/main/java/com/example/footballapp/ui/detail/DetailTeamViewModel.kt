package com.example.footballapp.ui.detail

import androidx.lifecycle.ViewModel
import com.example.footballapp.core.domain.model.Team
import com.example.footballapp.core.domain.usecase.FootballUseCase

class DetailTeamViewModel(private val footballUseCase: FootballUseCase) : ViewModel() {
    fun setFavoriteTeam(team: Team, newState: Boolean) = footballUseCase.setFavoriteTeam(team, newState)
}