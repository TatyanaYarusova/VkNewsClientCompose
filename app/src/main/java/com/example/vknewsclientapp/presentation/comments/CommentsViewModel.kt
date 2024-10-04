package com.example.vknewsclientapp.presentation.comments

import androidx.lifecycle.ViewModel
import com.example.vknewsclientapp.domain.entity.FeedPost
import com.example.vknewsclientapp.domain.usecase.GetCommentsUseCase
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private val feedPost: FeedPost,
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {

    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                comments = it,
                post = feedPost
            )
        }

}