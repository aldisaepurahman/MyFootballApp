package com.example.footballapp.ui.standings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.footballapp.core.domain.usecase.FootballUseCase

class StandingsViewModel(footballUseCase: FootballUseCase): ViewModel() {
    private val _leagueId = MutableLiveData<String>()

    init {
        _leagueId.value = "4328"
    }

    val standings = Transformations.switchMap(_leagueId) {
        footballUseCase.getLeagueStandings(it).asLiveData()
    }

    val leagues = footballUseCase.getAllLeague().asLiveData()

    fun switchLeague(newLeagueId: String) {
        _leagueId.value = newLeagueId
    }
}