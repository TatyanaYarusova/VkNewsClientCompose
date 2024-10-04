package com.example.vknewsclientapp.domain.usecase

import com.example.vknewsclientapp.domain.repository.NewsFeedRepository
import com.example.vknewsclientapp.domain.entity.FeedPost
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetRecommendationUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.getRecommendations()
    }
}