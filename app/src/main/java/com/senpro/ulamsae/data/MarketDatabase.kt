package com.senpro.ulamsae.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.senpro.ulamsae.model.Market

@Database(entities = [Market::class], version = 1)
@TypeConverters(FileConverter::class)
abstract class MarketDatabase : RoomDatabase() {
    abstract fun marketDao(): MarketDao

    companion object {
        @Volatile
        private var INSTANCE: MarketDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MarketDatabase {
            if (INSTANCE == null) {
                synchronized(MarketDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MarketDatabase::class.java, "market"
                    )
                        .build()
                }
            }
            return INSTANCE as MarketDatabase
        }
    }
}