package com.example.vknewsclientapp.presentation.news

import com.example.vknewsclientapp.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial: NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>): NewsFeedScreenState()
}