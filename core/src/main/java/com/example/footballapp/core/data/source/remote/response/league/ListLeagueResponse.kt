package com.example.footballapp.core.data.source.remote.response.league

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListLeagueResponse(

    @field:SerializedName("leagues")
    val leagues: List<LeagueResponse>? = null
) : Parcelable
