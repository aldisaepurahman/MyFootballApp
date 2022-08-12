package com.example.footballapp.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "team")
data class TeamEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "teamId")
    var teamId: String,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "stadiumLocation")
    var stadiumLocation: String? = null,

    @ColumnInfo(name = "formedYear")
    var formedYear: String? = null,

    @ColumnInfo(name = "stadium")
    var stadium: String? = null,

    @ColumnInfo(name = "logo")
    var logo: String? = null,

    @ColumnInfo(name = "leagueName")
    var leagueName: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
