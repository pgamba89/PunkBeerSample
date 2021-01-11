package com.example.punkBeerSample.utils

import com.example.punkBeerSample.model.BeerModel
import java.util.concurrent.atomic.AtomicInteger

object BeersFactory {
    private val counter = AtomicInteger(0)

    fun createBeer(name: String): BeerModel {
        val id = counter.incrementAndGet()
        return BeerModel(
            id = id.toLong(),
            name = "name_$id",
            tagline = null,
            first_brewed = null,
            description = "description_$id",
            image_url = null,
            abv = null,
            ibu = null,
            target_fg = null,
            target_og = null,
            ebc = null,
            srm = null,
            ph = null,
            attenuation_level = null,
            volume = null,
            boil_volume = null,
            method = null,
            ingredients = null,
            food_pairing = null,
            brewers_tips = null,
            contributed_by = null
        )
    }
}