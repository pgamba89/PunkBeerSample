package com.example.punkBeerSample.di

import android.content.Context
import com.example.punkBeerSample.api.ApiService
import com.example.punkBeerSample.db.BeersDao
import com.example.punkBeerSample.db.BeersDatabase
import com.example.punkBeerSample.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): BeersDatabase {
        return BeersDatabase.getInstance(context)
    }

    @Provides
    fun provideRepoDao(appDatabase: BeersDatabase): BeersDao {
        return appDatabase.beersDao()
    }

}