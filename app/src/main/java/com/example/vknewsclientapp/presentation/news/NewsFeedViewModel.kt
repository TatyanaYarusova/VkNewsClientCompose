package com.example.vknewsclientapp.presentation.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vknewsclientapp.data.repository.NewsFeedRepository
import com.example.vknewsclientapp.domain.FeedPost
import com.example.vknewsclientapp.domain.StatisticItem
import kotlinx.coroutines.launch

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val initialState = NewsFeedScreenState.Initial

    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    private val repository = NewsFeedRepository(getApplication())

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            val response = repository.loadRecommendation()
            _screenState.value = NewsFeedScreenState.Posts(posts = response)
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.changeLikeStatus(feedPost)
            _screenState.value = NewsFeedScreenState.Posts(posts = repository.feedPosts)
        }
    }


    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        if(currentState !is NewsFeedScreenState.Posts) return
        val oldPosts = currentState.posts.toMutableList()
        val oldStatistic = feedPost.statistic
        val newStatistics = oldStatistic.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistic = newStatistics)
        val newPosts = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = NewsFeedScreenState.Posts(posts = newPosts)
    }

    fun remove(item: FeedPost) {
        val currentState = screenState.value
        if(currentState !is NewsFeedScreenState.Posts) return
        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(item)
        _screenState.value = NewsFeedScreenState.Posts(posts = oldPosts)
    }
}