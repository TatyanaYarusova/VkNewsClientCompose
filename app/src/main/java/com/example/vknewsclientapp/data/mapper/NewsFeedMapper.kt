package com.example.vknewsclientapp.data.mapper

import com.example.vknewsclientapp.data.model.NewsFeedResponseDto
import com.example.vknewsclientapp.domain.FeedPost
import com.example.vknewsclientapp.domain.StatisticItem
import com.example.vknewsclientapp.domain.StatisticType
import kotlin.math.absoluteValue

fun NewsFeedResponseDto.dtoToEntity(): List<FeedPost> {
    val result = mutableListOf<FeedPost>()
    val posts = this.newsFeedContent.posts
    val groups = this.newsFeedContent.groups

    for(post in posts) {
        val group = groups.find { it.id == post.communityId.absoluteValue } ?: break
        val feedPost = FeedPost(
            id = post.id,
            communityName = group.name,
            publicationDate = post.date.toString(),
            communityImageUrl = group.imageUrl,
            contentText = post.text,
            contentImageUrl = post.attachment?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
            statistic = listOf(
                StatisticItem(type = StatisticType.LIKES, post.likes.count),
                StatisticItem(type = StatisticType.VIEWS, post.views.count),
                StatisticItem(type = StatisticType.SHARES, post.reposts.count),
                StatisticItem(type = StatisticType.COMMENTS, post.comments.count)
            )
        )
        result.add(feedPost)
    }

    return result
}