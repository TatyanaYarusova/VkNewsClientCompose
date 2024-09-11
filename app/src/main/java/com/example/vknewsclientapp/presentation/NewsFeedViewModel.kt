package com.example.vknewsclientapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclientapp.domain.FeedPost
import com.example.vknewsclientapp.domain.StatisticItem
import com.example.vknewsclientapp.ui.NewsFeedScreenState

class NewsFeedViewModel : ViewModel() {

    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(FeedPost(id = it))
        }
    }

    private val initialState = NewsFeedScreenState.Posts(sourceList)

    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState


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
        _screenState.value =NewsFeedScreenState.Posts(posts = newPosts)
    }

    fun remove(item: FeedPost) {
        val currentState = screenState.value
        if(currentState !is NewsFeedScreenState.Posts) return
        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(item)
        _screenState.value = NewsFeedScreenState.Posts(posts = oldPosts)
    }
}