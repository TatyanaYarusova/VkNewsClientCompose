package com.example.vknewsclientapp.presentation.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.vknewsclientapp.data.repository.NewsFeedRepositoryImpl
import com.example.vknewsclientapp.domain.entity.FeedPost
import com.example.vknewsclientapp.domain.usecase.GetCommentsUseCase
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : ViewModel() {

    private val repository = NewsFeedRepositoryImpl(application)

    private val getCommentsUseCase = GetCommentsUseCase(repository)

    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                comments = it,
                post = feedPost
            )
        }

}