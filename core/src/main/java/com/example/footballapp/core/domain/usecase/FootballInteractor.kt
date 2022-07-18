package com.example.footballapp.core.domain.usecase

import com.example.footballapp.core.data.Result
import com.example.footballapp.core.domain.model.League
import com.example.footballapp.core.domain.model.Standings
import com.example.footballapp.core.domain.model.Team
import com.example.footballapp.core.domain.repository.IFootballRepository
import com.example.footballapp.core.utils.leagueId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FootballInteractor(private val footballRepository: IFootballRepository) : FootballUseCase {
    override fun getAllTeam(leagueName: String): Flow<Result<List<Team>>> =
        footballRepository.getAllTeamOnLeague(leagueName)

    override fun getAllLeague(): Flow<List<League>> =
        footballRepository.getAllLeague().map { it -> it.filter { it.leagueId.toInt() in leagueId } }

    override fun getLeagueStandings(leagueId: String): Flow<Result<List<Standings>>> =
        footballRepository.getLeagueStandings(leagueId)

    override fun getFavoritesTeam(): Flow<List<Team>> = footballRepository.getFavoritesTeam()

    override fun setFavoriteTeam(team: Team, state: Boolean) = footballRepository.setFavoriteTeam(team, state)

}