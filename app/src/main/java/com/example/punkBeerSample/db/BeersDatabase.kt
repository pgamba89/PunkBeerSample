package com.example.punkBeerSample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.punkBeerSample.model.BeerModel
import com.example.punkBeerSample.utils.Converters

@Database(
    entities = [BeerModel::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    Converters.HopsConverter::class,
    Converters.MaltConverter::class,
    Converters.MashTempConverter::class,
    Converters.FoodPairingConverter::class
)
abstract class BeersDatabase : RoomDatabase() {

    abstract fun beersDao(): BeersDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {

        @Volatile
        private var INSTANCE: BeersDatabase? = null

        fun getInstance(context: Context): BeersDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BeersDatabase::class.java, "Beers.db"
            ).build()
    }
}