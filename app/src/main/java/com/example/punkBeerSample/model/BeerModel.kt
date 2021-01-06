package com.example.punkBeerSample.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "beers")
@Parcelize
data class BeerModel(
    @PrimaryKey
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("tagline") val tagline: String?,
    @field:SerializedName("first_brewed") val first_brewed: String?,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("image_url") val image_url: String?,
    @field:SerializedName("abv") val abv: Double?,
    @field:SerializedName("ibu") val ibu: Double?,
    @field:SerializedName("target_fg") val target_fg: Double?,
    @field:SerializedName("target_og") val target_og: Double?,
    @field:SerializedName("ebc") val ebc: Double?,
    @field:SerializedName("srm") val srm: Double?,
    @field:SerializedName("ph") val ph: Double?,
    @field:SerializedName("attenuation_level") val attenuation_level: Double?,
    @field:SerializedName("volume")
    @field:Embedded(prefix = "volume_") val volume: BoilVolume?,
    @field:SerializedName("boil_volume")
    @field:Embedded(prefix = "boil_volume_") val boil_volume: BoilVolume?,
    @field:SerializedName("method")
    @field:Embedded(prefix = "method_") val method: Method?,
    @field:SerializedName("ingredients")
    @field:Embedded(prefix = "ingredients_") val ingredients: Ingredients?,
    @field:SerializedName("food_pairing")
    val food_pairing: List<String>?,
    @field:SerializedName("brewers_tips") val brewers_tips: String?,
    @field:SerializedName("contributed_by") val contributed_by: String?
) : Parcelable