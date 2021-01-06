package com.example.punkBeerSample.model

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hop (
        val name: String?,
        @field:Embedded(prefix = "amount_")
        val amount: BoilVolume?,
        val add: String?,
        val attribute: String?
) : Parcelable