package com.example.footballapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val teamId: String,
    val name: String? = null,
    val description: String? = null,
    val formedYear: String? = null,
    val stadium: String? = null,
    val logo: String? = null,
    val leagueName: String? = null,
    val isFavorite: Boolean = false
) : Parcelable
