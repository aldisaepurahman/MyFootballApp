package com.example.footballapp.di

import com.example.footballapp.core.domain.usecase.FootballInteractor
import com.example.footballapp.core.domain.usecase.FootballUseCase
import com.example.footballapp.ui.detail.DetailTeamViewModel
import com.example.footballapp.ui.home.HomeViewModel
import com.example.footballapp.ui.standings.StandingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FootballUseCase> { FootballInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { StandingsViewModel(get()) }
    viewModel { DetailTeamViewModel(get()) }
}