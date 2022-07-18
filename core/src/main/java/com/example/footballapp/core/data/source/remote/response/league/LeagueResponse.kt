package com.example.footballapp.core.data.source.remote.response.league

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueResponse(

	@field:SerializedName("strLeagueAlternate")
	val strLeagueAlternate: String? = null,

	@field:SerializedName("strLeague")
	val strLeague: String,

	@field:SerializedName("strSport")
	val strSport: String,

	@field:SerializedName("idLeague")
	val idLeague: String
) : Parcelable
