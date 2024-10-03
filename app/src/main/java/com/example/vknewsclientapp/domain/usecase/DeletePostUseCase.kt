package com.example.vknewsclientapp.domain.usecase

import com.example.vknewsclientapp.domain.entity.FeedPost
import com.example.vknewsclientapp.domain.repository.NewsFeedRepository

class DeletePostUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) {
        return repository.deletePost(feedPost)
    }
}