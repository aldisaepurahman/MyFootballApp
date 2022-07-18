package com.example.footballapp.core.data.source.remote

import com.example.footballapp.core.data.source.remote.network.ApiResponse
import com.example.footballapp.core.data.source.remote.network.ApiService
import com.example.footballapp.core.data.source.remote.response.league.LeagueResponse
import com.example.footballapp.core.data.source.remote.response.standings.StandingsResponse
import com.example.footballapp.core.data.source.remote.response.team.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    fun getLeagueStandings(leagueId: String) : Flow<List<StandingsResponse>> {
        return flow {
            apiService.getLeagueStandings(leagueId).table?.map {
                StandingsResponse(
                    strTeam = it.strTeam,
                    intGoalsAgainst = it.intGoalsAgainst,
                    intPoints = it.intPoints,
                    intDraw = it.intDraw,
                    intGoalDifference = it.intGoalDifference,
                    intGoalsFor = it.intGoalsFor,
                    intPlayed = it.intPlayed,
                    strTeamBadge = it.strTeamBadge,
                    strDescription = it.strDescription,
                    intRank = it.intRank,
                    intLoss = it.intLoss,
                    intWin = it.intWin
                )
            }?.let { emit(it) }
        }
    }

    fun getAllLeague(): Flow<List<LeagueResponse>> {
        return flow {
            apiService.getAllLeague().leagues?.map {
                LeagueResponse(
                    strLeagueAlternate = it.strLeagueAlternate,
                    strLeague = it.strLeague,
                    strSport = it.strSport,
                    idLeague = it.idLeague
                )
            }?.let { emit(it) }
        }
    }

    suspend fun getAllTeamOnLeague(leagueName: String): Flow<ApiResponse<List<TeamResponse>>> {
        return flow {
            try {
                val response = apiService.getAllTeamOnLeague(leagueName)
                val dataArray = response.teams
                if (dataArray?.isNotEmpty() == true) {
                    emit(ApiResponse.Success(response.teams))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}