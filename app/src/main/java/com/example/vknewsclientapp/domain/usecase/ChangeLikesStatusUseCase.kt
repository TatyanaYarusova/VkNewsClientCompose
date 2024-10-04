package com.example.vknewsclientapp.domain.usecase

import com.example.vknewsclientapp.domain.entity.FeedPost
import com.example.vknewsclientapp.domain.repository.NewsFeedRepository
import javax.inject.Inject

class ChangeLikesStatusUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) {
        return repository.changeLikeStatus(feedPost)
    }
}