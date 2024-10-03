package com.example.vknewsclientapp.presentation.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.vknewsclientapp.data.repository.NewsFeedRepository
import com.example.vknewsclientapp.domain.FeedPost
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : ViewModel() {

    private val repository = NewsFeedRepository(application)

    val screenState = repository.getComments(feedPost)
        .map {
            CommentsScreenState.Comments(
                comments = it,
                post = feedPost
            )
        }

}