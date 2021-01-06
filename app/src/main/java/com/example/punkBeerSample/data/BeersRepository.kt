package com.example.punkBeerSample.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.punkBeerSample.api.ApiService
import com.example.punkBeerSample.db.BeersDatabase
import com.example.punkBeerSample.model.BeerModel
import com.example.punkBeerSample.utils.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class BeersRepository @Inject constructor(
    private val apiService: ApiService,
    private val database: BeersDatabase
) {
    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getBeers(queryString: String): LiveData<PagingData<BeerModel>> {

        val dbQuery = "%${queryString.replace(' ', '%')}%"
        val pagingSourceFactory = { database.beersDao().beerById(dbQuery) }

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = RemoteMediator(
                queryString,
                apiService,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
    }
}