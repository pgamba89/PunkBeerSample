package com.example.punkBeerSample.api

import com.example.punkBeerSample.model.BeerModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("beers/")
    suspend fun getBeers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): List<BeerModel>
}
