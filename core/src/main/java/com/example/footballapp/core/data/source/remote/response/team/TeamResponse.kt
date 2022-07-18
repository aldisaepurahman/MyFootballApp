package com.example.footballapp.core.data.source.remote.response.team

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamResponse(

    @field:SerializedName("strTeamBadge")
    val strTeamLogo: String? = null,

    @field:SerializedName("intFormedYear")
    val intFormedYear: String? = null,

    @field:SerializedName("idTeam")
    val idTeam: String,

    @field:SerializedName("strDescriptionEN")
    val strDescriptionEN: String? = null,

    @field:SerializedName("strTeam")
    val strTeam: String? = null,

    @field:SerializedName("strStadium")
    val strStadium: String? = null,

    @field:SerializedName("strLeague")
    val strLeague: String? = null,
) : Parcelable

