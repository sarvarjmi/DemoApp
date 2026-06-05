package com.demoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demoapp.data.local.entity.HomeFeedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeFeedDao {
    @Query("SELECT * FROM home_feed ORDER BY rank ASC")
    fun getHomeFeed(): Flow<List<HomeFeedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHomeFeed(feed: List<HomeFeedEntity>)

    @Query("DELETE FROM home_feed")
    suspend fun deleteAll()

    @androidx.room.Transaction
    suspend fun refreshFeed(feed: List<HomeFeedEntity>) {
        deleteAll()
        insertHomeFeed(feed)
    }
}
