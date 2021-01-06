package com.example.punkBeerSample.model

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Method (
    val mash_temp: List<MashTemp>?,
    @field:Embedded(prefix = "fermentation_")
    val fermentation: Fermentation?,
    val twist: String?
) : Parcelable