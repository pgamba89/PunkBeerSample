package com.example.punkBeerSample.beersList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.example.punkBeerSample.data.BeersRepository
import com.example.punkBeerSample.model.BeerModel
import com.example.punkBeerSample.ui.beersList.BeersModelView
import com.example.punkBeerSample.utils.Constants.Companion.DEFAULT_QUERY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import java.io.InputStreamReader
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class BeersModelViewTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    fun runBlockingTest(
        context: CoroutineContext = EmptyCoroutineContext,
        testBody: suspend TestCoroutineScope.() -> Unit
    ): Unit {
    }

    @Mock
    private lateinit var repository: BeersRepository

    @Spy
    private var beersListLiveData: MutableLiveData<PagingData<BeerModel>> = MutableLiveData()

    lateinit var modelView: BeersModelView

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        modelView = BeersModelView(repository)
    }

    @Test
    fun getBeers() = runBlockingTest {
        modelView.currentList?.observeForever { }

        // given
        val response = MockResponseFileReader("response.json")
        val sType = object : TypeToken<List<BeerModel>>() {}.type
        val list = Gson().fromJson<List<BeerModel>>(response.content, sType)

        beersListLiveData.value = PagingData.from(list)
        Mockito.`when`(repository.getBeers(DEFAULT_QUERY)).thenReturn(beersListLiveData)

        assertEquals(beersListLiveData.value, modelView.currentList?.value)
    }
}

class MockResponseFileReader(path: String) {
    val content: String
    init {
        val reader = InputStreamReader(this.javaClass.classLoader.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }
}