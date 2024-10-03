package com.example.vknewsclientapp.presentation.news

import com.example.vknewsclientapp.domain.entity.FeedPost

sealed class NewsFeedScreenState {

    object Initial: NewsFeedScreenState()

    object Loading: NewsFeedScreenState()

    data class Posts(
        val posts: List<FeedPost>,
        val nextDataIsLoading: Boolean = false
    ): NewsFeedScreenState()

}