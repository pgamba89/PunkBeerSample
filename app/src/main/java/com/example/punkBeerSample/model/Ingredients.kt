package com.example.punkBeerSample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredients(
        val malt: List<Malt>?,
        val hops: List<Hop>?,
        val yeast: String?
) : Parcelable