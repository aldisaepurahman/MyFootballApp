package com.example.footballapp.core.data.source.remote.response.standings

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StandingsResponse(

    @field:SerializedName("strTeam")
    val strTeam: String? = null,

    @field:SerializedName("intGoalsAgainst")
    val intGoalsAgainst: String? = null,

    @field:SerializedName("intPoints")
    val intPoints: String? = null,

    @field:SerializedName("intGoalDifference")
    val intGoalDifference: String? = null,

    @field:SerializedName("intGoalsFor")
    val intGoalsFor: String? = null,

    @field:SerializedName("intPlayed")
    val intPlayed: String? = null,

    @field:SerializedName("strTeamBadge")
    val strTeamBadge: String? = null,

    @field:SerializedName("strDescription")
    val strDescription: String? = null,

    @field:SerializedName("intDraw")
    val intDraw: String? = null,

    @field:SerializedName("intRank")
    val intRank: String? = null,

    @field:SerializedName("intLoss")
    val intLoss: String? = null,

    @field:SerializedName("intWin")
    val intWin: String? = null
) : Parcelable

