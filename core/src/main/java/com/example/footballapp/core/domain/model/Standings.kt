package com.example.footballapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Standings(
    val strTeam: String? = null,
    val intGoalsAgainst: String? = null,
    val intPoints: String? = null,
    val intGoalDifference: String? = null,
    val intGoalsFor: String? = null,
    val intPlayed: String? = null,
    val strTeamBadge: String? = null,
    val strDescription: String? = null,
    val intDraw: String? = null,
    val intRank: String? = null,
    val intLoss: String? = null,
    val intWin: String? = null
) : Parcelable