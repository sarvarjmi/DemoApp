package com.demoapp.data.remote

import com.demoapp.data.remote.dto.HomeFeedDto
import retrofit2.http.GET

interface HomeFeedApi {
    @GET("c/6df5-7432-4a7e-85e2")
    suspend fun getHomeFeed(): HomeFeedDto

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
}
