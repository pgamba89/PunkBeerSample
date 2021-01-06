package com.example.punkBeerSample.ui.beersList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.punkBeerSample.data.BeersRepository
import com.example.punkBeerSample.model.BeerModel
import java.io.IOException

class BeersModelView @ViewModelInject constructor(private val repository: BeersRepository) :
    ViewModel() {

    private var currentQueryValue: String? = null

    var currentList: LiveData<PagingData<BeerModel>>? = null

    fun getBeersList(queryString: String) {
        val lastResult = currentList
        try {
            if (!(queryString == currentQueryValue && lastResult != null)) {
                currentQueryValue = queryString
                val newResult: LiveData<PagingData<BeerModel>> =
                    repository.getBeers(queryString).cachedIn(viewModelScope)
                currentList = newResult
            }
        } catch (exception: IOException){

        }
    }
}