package com.example.punkBeerSample.utils

import androidx.room.TypeConverter
import com.example.punkBeerSample.model.Hop
import com.example.punkBeerSample.model.Malt
import com.example.punkBeerSample.model.MashTemp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Converters {

    class HopsConverter {
        private var gson = Gson()
        @TypeConverter
        fun stringToSomeObjectList(data: String?): List<Hop?>? {
            if (data == null) {
                return Collections.emptyList()
            }
            val listType: Type = object : TypeToken<List<Hop?>?>() {}.getType()
            return gson.fromJson<List<Hop?>>(data, listType)
        }

        @TypeConverter
        fun someObjectListToString(someObjects: List<Hop?>?): String? {
            return gson.toJson(someObjects)
        }
    }

    class MaltConverter {
        private var gson = Gson()
        @TypeConverter
        fun stringToSomeObjectList(data: String?): List<Malt?>? {
            if (data == null) {
                return Collections.emptyList()
            }
            val listType: Type = object : TypeToken<List<Malt?>?>() {}.getType()
            return gson.fromJson<List<Malt?>>(data, listType)
        }

        @TypeConverter
        fun someObjectListToString(someObjects: List<Malt?>?): String? {
            return gson.toJson(someObjects)
        }
    }

    class MashTempConverter {
        private var gson = Gson()
        @TypeConverter
        fun stringToSomeObjectList(data: String?): List<MashTemp?>? {
            if (data == null) {
                return Collections.emptyList()
            }
            val listType: Type = object : TypeToken<List<MashTemp?>?>() {}.getType()
            return gson.fromJson<List<MashTemp?>>(data, listType)
        }

        @TypeConverter
        fun someObjectListToString(someObjects: List<MashTemp?>?): String? {
            return gson.toJson(someObjects)
        }
    }

    class FoodPairingConverter {
        private var gson = Gson()
        @TypeConverter
        fun stringToSomeObjectList(data: String?): List<String?>? {
            if (data == null) {
                return Collections.emptyList()
            }
            val listType: Type = object : TypeToken<List<String?>?>() {}.getType()
            return gson.fromJson<List<String?>>(data, listType)
        }

        @TypeConverter
        fun someObjectListToString(someObjects: List<String?>?): String? {
            return gson.toJson(someObjects)
        }
    }
}