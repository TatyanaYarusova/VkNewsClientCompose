package com.example.vknewsclientapp.domain.repository

import com.example.vknewsclientapp.domain.entity.AuthState
import com.example.vknewsclientapp.domain.entity.FeedPost
import com.example.vknewsclientapp.domain.entity.PostComment
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepository {

    fun getAuthStateFlow(): StateFlow<AuthState>

    fun getRecommendations(): StateFlow<List<FeedPost>>

    fun getComments(feedPost: FeedPost): StateFlow<List<PostComment>>

    suspend fun loadNextData()

    suspend fun checkAuthState()

    suspend fun changeLikeStatus(feedPost: FeedPost)

    suspend fun deletePost(feedPost: FeedPost)


}