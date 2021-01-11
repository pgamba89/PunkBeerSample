package com.example.punkBeerSample.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.punkBeerSample.utils.BeersFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@RunWith(AndroidJUnit4::class)
class BeersDaoTest : BeersDatabaseTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    fun runBlockingTest(
        context: CoroutineContext = EmptyCoroutineContext,
        testBody: suspend TestCoroutineScope.() -> Unit
    ): Unit {
    }

    @Test
    fun insertAll_getAll() = runBlockingTest {
        val list = listOf(BeersFactory.createBeer("DEFAULT"), BeersFactory.createBeer("DEFAULT2"))
        db.beersDao().insertAll(list)
        val beers = db.beersDao().getAll()

        assertThat(beers, notNullValue())
        assertEquals(beers.size, 2)
    }

}