package com.example.footballapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val leagueId: String,
    val name: String,
    val alternateName: String? = null,
    val sports: String
) : Parcelable
