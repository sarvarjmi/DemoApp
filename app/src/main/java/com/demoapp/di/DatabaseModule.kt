package com.demoapp.di

import android.content.Context
import androidx.room.Room
import com.demoapp.data.local.dao.HomeFeedDao
import com.demoapp.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "demo_app_db"
        ).build()
    }

    @Provides
    fun provideHomeFeedDao(database: AppDatabase): HomeFeedDao {
        return database.homeFeedDao()
    }
}
