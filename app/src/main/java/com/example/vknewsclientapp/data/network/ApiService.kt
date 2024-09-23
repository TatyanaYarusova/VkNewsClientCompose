package com.example.vknewsclientapp.data.network

import com.example.vknewsclientapp.data.model.NewsFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("newsfeed.getRecommended?v=5.199")
    suspend fun loadWall(
        @Query("access_token") token: String
    ): NewsFeedResponseDto
}