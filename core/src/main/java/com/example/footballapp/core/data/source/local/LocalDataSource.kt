package com.example.footballapp.core.data.source.local

import com.example.footballapp.core.data.source.local.entity.TeamEntity
import com.example.footballapp.core.data.source.local.room.TeamDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val teamDao: TeamDao) {

    fun getAllTeamOnLeague(leagueName: String): Flow<List<TeamEntity>> = teamDao.getAllTeamOnLeague(leagueName)

    fun getFavoritesTeam(): Flow<List<TeamEntity>> = teamDao.getFavoritesTeam()

    fun insertTeam(team: List<TeamEntity>) = teamDao.insertTeam(team)

    fun updateFavoriteTeam(teamEntity: TeamEntity, newState: Boolean) {
        teamEntity.isFavorite = newState
        teamDao.updateFavoriteTeam(teamEntity)
    }
}