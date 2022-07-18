package com.example.footballapp.core.data.source.local.room

import androidx.room.*
import com.example.footballapp.core.data.source.local.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Query("SELECT * FROM team WHERE leagueName = :leagueName")
    fun getAllTeamOnLeague(leagueName: String): Flow<List<TeamEntity>>

    @Query("SELECT * FROM team WHERE isFavorite = 1")
    fun getFavoritesTeam():Flow<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: List<TeamEntity>)

    @Update
    fun updateFavoriteTeam(teamEntity: TeamEntity)
}