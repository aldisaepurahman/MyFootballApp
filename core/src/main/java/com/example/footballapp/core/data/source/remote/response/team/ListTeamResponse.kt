package com.example.footballapp.core.data.source.remote.response.team

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListTeamResponse(

	@field:SerializedName("teams")
	val teams: List<TeamResponse>? = null
) : Parcelable
