package com.demoapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demoapp.data.local.dao.HomeFeedDao
import com.demoapp.data.local.entity.HomeFeedEntity

@Database(entities = [HomeFeedEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun homeFeedDao(): HomeFeedDao
}
