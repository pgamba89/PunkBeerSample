package com.example.punkBeerSample.ui.beerDetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.punkBeerSample.model.BeerModel

class BeerDetailModelView @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _beerSelected = MutableLiveData<BeerModel>()

    val beerSelected: LiveData<BeerModel>
        get() = _beerSelected

    init {
        val repo = savedStateHandle.get<BeerModel>("beerSelected")!!
        _beerSelected.value = repo
    }
}