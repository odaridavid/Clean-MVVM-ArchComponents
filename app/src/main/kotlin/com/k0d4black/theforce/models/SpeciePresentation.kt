package com.k0d4black.theforce.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
internal data class SpeciePresentation(val name: String, val language: String) : Parcelable
