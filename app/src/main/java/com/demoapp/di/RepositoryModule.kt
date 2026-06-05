package com.demoapp.di

import com.demoapp.data.repository.HomeFeedRepositoryImpl
import com.demoapp.domain.repository.HomeFeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHomeFeedRepository(
        homeFeedRepositoryImpl: HomeFeedRepositoryImpl
    ): HomeFeedRepository
}
