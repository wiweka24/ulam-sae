package com.senpro.ulamsae.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senpro.ulamsae.model.Market

@Dao
interface MarketDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFish(market: Market)

    @Query("SELECT * FROM market")
    fun getAllFish(): LiveData<List<Market>>
}