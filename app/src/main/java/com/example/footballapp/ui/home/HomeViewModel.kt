package com.example.footballapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.footballapp.core.domain.usecase.FootballUseCase

class HomeViewModel(footballUseCase: FootballUseCase) : ViewModel() {

    private val _leagueName = MutableLiveData<String>()

    init {
        _leagueName.value = "English Premier League"
    }

    val teams = Transformations.switchMap(_leagueName) {
        footballUseCase.getAllTeam(it).asLiveData()
    }

    val leagues = footballUseCase.getAllLeague().asLiveData()

    fun switchLeague(newLeague: String) {
        _leagueName.value = newLeague
    }

}