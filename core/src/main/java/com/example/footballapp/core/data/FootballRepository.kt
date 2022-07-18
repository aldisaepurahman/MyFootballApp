package com.example.footballapp.core.data

import com.example.footballapp.core.data.source.local.LocalDataSource
import com.example.footballapp.core.data.source.remote.RemoteDataSource
import com.example.footballapp.core.data.source.remote.network.ApiResponse
import com.example.footballapp.core.data.source.remote.response.team.TeamResponse
import com.example.footballapp.core.domain.model.League
import com.example.footballapp.core.domain.model.Standings
import com.example.footballapp.core.domain.model.Team
import com.example.footballapp.core.domain.repository.IFootballRepository
import com.example.footballapp.core.utils.AppExecutors
import com.example.footballapp.core.utils.DataMapping
import kotlinx.coroutines.flow.*

class FootballRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IFootballRepository {
    override fun getAllTeamOnLeague(leagueName: String): Flow<Result<List<Team>>> =
        object : NetworkBoundResult<List<Team>, List<TeamResponse>>() {
            override fun loadFromDB(): Flow<List<Team>> {
                return localDataSource.getAllTeamOnLeague(leagueName).map {
                    DataMapping.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Team>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TeamResponse>>> =
                remoteDataSource.getAllTeamOnLeague(leagueName)

            override suspend fun saveCallResult(data: List<TeamResponse>) {
                val teamList = DataMapping.mapResponsesToEntities(data)
                appExecutors.diskIO().execute { localDataSource.insertTeam(teamList) }
            }
        }.asFlow()

    override fun getAllLeague(): Flow<List<League>> =
        remoteDataSource.getAllLeague().map {
            DataMapping.mapResponsesToDomain(it)
        }

    override fun getLeagueStandings(leagueId: String): Flow<Result<List<Standings>>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getLeagueStandings(leagueId).first()
            emit(Result.Success(DataMapping.mapStandingsResponseToStandingsDomain(response)))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
        /*remoteDataSource.getLeagueStandings(leagueId).map {
            DataMapping.mapStandingsResponseToStandingsDomain(it)
        }*/

    override fun getFavoritesTeam(): Flow<List<Team>> =
        localDataSource.getFavoritesTeam().map {
            DataMapping.mapEntitiesToDomain(it)
        }

    override fun setFavoriteTeam(team: Team, state: Boolean) {
        val teamEntity = DataMapping.mapDomainToEntity(team)
        appExecutors.diskIO().execute { localDataSource.updateFavoriteTeam(teamEntity, state) }
    }
}