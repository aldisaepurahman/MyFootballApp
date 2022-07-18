package com.example.footballapp.core.domain.usecase

import com.example.footballapp.core.data.Result
import com.example.footballapp.core.domain.model.League
import com.example.footballapp.core.domain.model.Standings
import com.example.footballapp.core.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface FootballUseCase {
    fun getAllTeam(leagueName: String) : Flow<Result<List<Team>>>
    fun getAllLeague() : Flow<List<League>>
    fun getLeagueStandings(leagueId: String) : Flow<Result<List<Standings>>>
    fun getFavoritesTeam() : Flow<List<Team>>
    fun setFavoriteTeam(team: Team, state: Boolean)
}