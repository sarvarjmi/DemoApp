package com.demoapp.domain.repository

import com.demoapp.domain.model.HomeModule
import kotlinx.coroutines.flow.Flow

interface HomeFeedRepository {
    fun getHomeFeed(): Flow<List<HomeModule>>
    suspend fun refreshHomeFeed()
}
