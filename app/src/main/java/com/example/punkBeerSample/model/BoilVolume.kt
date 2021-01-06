package com.example.punkBeerSample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BoilVolume(
    val value: Double?,
    val unit: String?
) : Parcelable