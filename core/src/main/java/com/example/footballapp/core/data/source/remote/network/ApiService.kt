package com.example.footballapp.core.data.source.remote.network

import com.example.footballapp.core.data.source.remote.response.league.ListLeagueResponse
import com.example.footballapp.core.data.source.remote.response.standings.ListStandingsResponse
import com.example.footballapp.core.data.source.remote.response.team.ListTeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("all_leagues.php")
    suspend fun getAllLeague() : ListLeagueResponse

    @GET("search_all_teams.php?")
    suspend fun getAllTeamOnLeague(
        @Query("l") leagueName: String
    ) : ListTeamResponse

    @GET("lookuptable.php?")
    suspend fun getLeagueStandings(
        @Query("l") leagueId: String,
        @Query("s") season: String = "2021-2022"
    ) : ListStandingsResponse
}