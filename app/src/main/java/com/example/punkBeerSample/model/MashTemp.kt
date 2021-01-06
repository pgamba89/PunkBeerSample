package com.example.punkBeerSample.model

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MashTemp (
    @field:Embedded(prefix = "temp_")
    val temp: BoilVolume?,
    val duration: Long?
) : Parcelable
