package com.example.punkBeerSample.model

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Malt (
    val name: String?,
    @field:Embedded(prefix = "amount_")
    val amount: BoilVolume?
) : Parcelable