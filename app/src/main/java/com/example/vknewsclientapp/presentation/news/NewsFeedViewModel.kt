package com.example.vknewsclientapp.presentation.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclientapp.data.repository.NewsFeedRepositoryImpl
import com.example.vknewsclientapp.domain.entity.FeedPost
import com.example.vknewsclientapp.domain.usecase.ChangeLikesStatusUseCase
import com.example.vknewsclientapp.domain.usecase.DeletePostUseCase
import com.example.vknewsclientapp.domain.usecase.GetRecommendationUseCase
import com.example.vknewsclientapp.domain.usecase.LoadNestDataUseCase
import com.example.vknewsclientapp.extations.mergeWith
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val exceptionHandler = CoroutineExceptionHandler{ _, _ ->
        Log.d("NewsFeedViewModel", "Exception caught by exception handler")
    }

    private val repository = NewsFeedRepositoryImpl(getApplication())

    private val getRecommendationUseCase = GetRecommendationUseCase(repository)
    private val loadNestDataUseCase = LoadNestDataUseCase(repository)
    private val changeLikesStatusUseCase = ChangeLikesStatusUseCase(repository)
    private val deletePostUseCase = DeletePostUseCase(repository)

    private val recommendationFlow = getRecommendationUseCase()

    private val loadNextDataEvents = MutableSharedFlow<Unit>()
    private val loadNextDataFlow = flow {
        loadNextDataEvents.collect {
            emit(
                NewsFeedScreenState.Posts(
                    posts = recommendationFlow.value,
                    nextDataIsLoading = true
                )
            )
        }
    }

    val screenState = recommendationFlow
        .filter { it.isNotEmpty() }
        .map { NewsFeedScreenState.Posts(posts = it) as NewsFeedScreenState }
        .onStart { emit(NewsFeedScreenState.Loading) }
        .mergeWith(loadNextDataFlow)


    fun loadNextRecommendations() {
        viewModelScope.launch {
            loadNextDataEvents.emit(Unit)
            loadNestDataUseCase()
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            changeLikesStatusUseCase(feedPost)
        }
    }

    fun remove(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            deletePostUseCase(feedPost)
        }
    }
}