package com.example.vknewsclientapp.ui

import com.example.vknewsclientapp.domain.FeedPost
import com.example.vknewsclientapp.domain.PostComment

sealed class CommentsScreenState {
    object Initial: CommentsScreenState()

    data class Comments(val post: FeedPost, val comments: List<PostComment>): CommentsScreenState()
}