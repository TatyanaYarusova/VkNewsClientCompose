package com.example.vknewsclientapp.domain.usecase

import com.example.vknewsclientapp.domain.repository.NewsFeedRepository
import com.example.vknewsclientapp.domain.entity.FeedPost
import kotlinx.coroutines.flow.StateFlow

class GetRecommendationUseCase(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.getRecommendations()
    }
}