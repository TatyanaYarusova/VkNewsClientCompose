package com.example.vknewsclientapp.domain.usecase

import com.example.vknewsclientapp.domain.entity.FeedPost
import com.example.vknewsclientapp.domain.entity.PostComment
import com.example.vknewsclientapp.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(feedPost: FeedPost): StateFlow<List<PostComment>> {
        return repository.getComments(feedPost)
    }
}