package com.k0d4black.theforce.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
internal data class FilmPresentation(val title: String, val openingCrawl: String) : Parcelable
