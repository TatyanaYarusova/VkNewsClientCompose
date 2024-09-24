package com.example.vknewsclientapp.data.mapper

import com.example.vknewsclientapp.data.model.CommentsResponseDto
import com.example.vknewsclientapp.data.model.NewsFeedResponseDto
import com.example.vknewsclientapp.domain.FeedPost
import com.example.vknewsclientapp.domain.PostComment
import com.example.vknewsclientapp.domain.StatisticItem
import com.example.vknewsclientapp.domain.StatisticType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.absoluteValue

fun NewsFeedResponseDto.dtoToEntity(): List<FeedPost> {
    val result = mutableListOf<FeedPost>()
    val posts = this.newsFeedContent.posts
    val groups = this.newsFeedContent.groups

    for(post in posts) {
        val group = groups.find { it.id == post.communityId.absoluteValue } ?: break
        val feedPost = FeedPost(
            id = post.id,
            communityId = post.communityId,
            communityName = group.name,
            publicationDate = mapTimestampToDate(post.date),
            communityImageUrl = group.imageUrl,
            contentText = post.text,
            contentImageUrl = post.attachment?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
            statistic = listOf(
                StatisticItem(type = StatisticType.LIKES, post.likes.count),
                StatisticItem(type = StatisticType.VIEWS, post.views.count),
                StatisticItem(type = StatisticType.SHARES, post.reposts.count),
                StatisticItem(type = StatisticType.COMMENTS, post.comments.count)
            ),
            isLiked = post.likes.userLikes > 0

        )
        result.add(feedPost)
    }

    return result
}

fun CommentsResponseDto.dtoToEntity(): List<PostComment> {
    val result = mutableListOf<PostComment>()
    val comments = this.content.comments
    val profiles = this.content.profiles
    for (comment in comments) {
        if (comment.text.isBlank()) continue
        val author = profiles.firstOrNull { it.id == comment.authorId } ?: continue
        val postComment = PostComment(
            id = comment.id,
            authorName = "${author.firstName} ${author.lastName}",
            authorAvatarUrl = author.avatarUrl,
            commentText = comment.text,
            publicationDate = mapTimestampToDate(comment.date)
        )
        result.add(postComment)
    }

    return result
}

private fun mapTimestampToDate(timestamp: Long): String {
    val date = Date(timestamp * 1000)
    return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
}