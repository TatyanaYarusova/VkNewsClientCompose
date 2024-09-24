package com.example.vknewsclientapp.data.repository

import android.app.Application
import com.example.vknewsclientapp.data.mapper.dtoToEntity
import com.example.vknewsclientapp.data.network.ApiFactory
import com.example.vknewsclientapp.domain.FeedPost
import com.example.vknewsclientapp.domain.StatisticItem
import com.example.vknewsclientapp.domain.StatisticType
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken

class NewsFeedRepository(application: Application) {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val token = VKAccessToken.restore(storage)

    private val apiService = ApiFactory.apiService

    private val _feedPosts = mutableListOf<FeedPost>()
    val feedPosts: List<FeedPost> get() = _feedPosts.toList()

    suspend fun loadRecommendation(): List<FeedPost> {
        val response = apiService.loadWall(getAccessToken())
        val posts = response.dtoToEntity()
        _feedPosts.addAll(posts)
        return posts
    }

    private fun getAccessToken(): String {
        return token?.accessToken ?: throw IllegalStateException("Token is null")
    }

    suspend fun changeLikeStatus(feedPost: FeedPost) {
        val response = if (feedPost.isLiked) {
            apiService.deleteLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        } else {
            apiService.addLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        }

        val newLikesCount = response.likes.count
        val newStatistics = feedPost.statistic.toMutableList().apply {
            removeIf { it.type == StatisticType.LIKES }
            add(StatisticItem(type = StatisticType.LIKES, newLikesCount))
        }
        val newPost = feedPost.copy(statistic = newStatistics, isLiked = !feedPost.isLiked)
        val postIndex = _feedPosts.indexOf(feedPost)
        _feedPosts[postIndex] = newPost
    }

}