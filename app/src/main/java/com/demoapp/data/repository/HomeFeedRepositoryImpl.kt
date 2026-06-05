package com.demoapp.data.repository

import com.demoapp.data.local.dao.HomeFeedDao
import com.demoapp.data.mapper.toDomainModel
import com.demoapp.data.mapper.toEntity
import com.demoapp.data.remote.HomeFeedApi
import com.demoapp.domain.model.HomeModule
import com.demoapp.domain.repository.HomeFeedRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeFeedRepositoryImpl @Inject constructor(
    private val api: HomeFeedApi,
    private val dao: HomeFeedDao,
    private val gson: Gson
) : HomeFeedRepository {

    override fun getHomeFeed(): Flow<List<HomeModule>> {
        return dao.getHomeFeed().map { entities ->
            entities.map { it.toDomainModel(gson) }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun refreshHomeFeed() {
        withContext(Dispatchers.IO) {
            val response = api.getHomeFeed()
            val entities = response.result?.mapIndexed { index, moduleDto ->
                moduleDto.toEntity(index, gson)
            } ?: emptyList()
            dao.refreshFeed(entities)
        }
    }
}
