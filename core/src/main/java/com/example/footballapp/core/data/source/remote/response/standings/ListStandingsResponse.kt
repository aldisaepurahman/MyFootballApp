package com.example.footballapp.core.data.source.remote.response.standings

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListStandingsResponse(

	@field:SerializedName("table")
	val table: List<StandingsResponse>? = null
) : Parcelable