package com.example.vknewsclientapp.presentation.comments

import com.example.vknewsclientapp.domain.entity.FeedPost
import com.example.vknewsclientapp.domain.entity.PostComment

sealed class CommentsScreenState {
    object Initial: CommentsScreenState()

    data class Comments(val post: FeedPost, val comments: List<PostComment>): CommentsScreenState()
}