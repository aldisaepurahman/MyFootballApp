package com.example.footballapp.core.utils

import com.example.footballapp.core.data.source.local.entity.TeamEntity
import com.example.footballapp.core.data.source.remote.response.league.LeagueResponse
import com.example.footballapp.core.data.source.remote.response.standings.StandingsResponse
import com.example.footballapp.core.data.source.remote.response.team.TeamResponse
import com.example.footballapp.core.domain.model.League
import com.example.footballapp.core.domain.model.Standings
import com.example.footballapp.core.domain.model.Team

object DataMapping {
    fun mapResponsesToDomain(input: List<LeagueResponse>): List<League> {
        return input.map {
            League(
                leagueId = it.idLeague,
                name = it.strLeague,
                alternateName = it.strLeagueAlternate,
                sports = it.strSport
            )
        }
    }

    fun mapStandingsResponseToStandingsDomain(input: List<StandingsResponse>) : List<Standings> {
        return input.map {
            Standings(
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
        }
    }

    fun mapResponsesToEntities(input: List<TeamResponse>): List<TeamEntity> =
        input.map {
            TeamEntity(
                teamId = it.idTeam,
                name = it.strTeam,
                stadiumLocation = it.strStadiumLocation,
                description = it.strDescriptionEN,
                formedYear = it.intFormedYear,
                stadium = it.strStadium,
                logo = it.strTeamLogo,
                leagueName = it.strLeague
            )
        }

    fun mapEntitiesToDomain(input: List<TeamEntity>): List<Team> =
        input.map {
            Team(
                teamId = it.teamId,
                name = it.name,
                stadiumLocation = it.stadiumLocation,
                description = it.description,
                formedYear = it.formedYear,
                stadium = it.stadium,
                logo = it.logo,
                leagueName = it.leagueName,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Team): TeamEntity = TeamEntity(
        teamId = input.teamId,
        name = input.name,
        stadiumLocation = input.stadiumLocation,
        description = input.description,
        formedYear = input.formedYear,
        stadium = input.stadium,
        logo = input.logo,
        leagueName = input.leagueName,
        isFavorite = input.isFavorite
    )
}