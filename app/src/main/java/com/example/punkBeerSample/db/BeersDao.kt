package com.example.punkBeerSample.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.punkBeerSample.model.BeerModel

@Dao
interface BeersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(beers: List<BeerModel>)

    @Query("SELECT * FROM beers WHERE name LIKE :queryString")
    fun beerById(queryString: String): PagingSource<Int, BeerModel>

    @Query("DELETE FROM beers")
    suspend fun deleteAll()

}