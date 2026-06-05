package com.demoapp.domain.usecase

import com.demoapp.domain.model.HomeModule
import com.demoapp.domain.repository.HomeFeedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeFeedUseCase @Inject constructor(
    private val repository: HomeFeedRepository
) {
    operator fun invoke(): Flow<List<HomeModule>> {
        return repository.getHomeFeed()
    }

    suspend fun refresh() {
        repository.refreshHomeFeed()
    }
}
