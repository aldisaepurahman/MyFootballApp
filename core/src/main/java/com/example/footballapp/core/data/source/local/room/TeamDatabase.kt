package com.example.footballapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.footballapp.core.data.source.local.entity.TeamEntity

@Database(entities = [TeamEntity::class], version = 1, exportSchema = false)
abstract class TeamDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
}