package com.example.vknewsclientapp.presentation.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclientapp.domain.FeedPost

@Composable
fun NewsFeedScreen(
    onCommentClickListener: (feedPost: FeedPost) -> Unit
) {
    val viewModel: NewsFeedViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.Initial)

    when (val currentState = screenState.value) {
        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                posts = currentState.posts,
                viewModel = viewModel,
                onCommentClickListener = onCommentClickListener

            )
        }
        NewsFeedScreenState.Initial -> {}
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedPosts(
    posts: List<FeedPost>,
    viewModel: NewsFeedViewModel,
    onCommentClickListener: (feedPost: FeedPost) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(
            top = 8.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 72.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = posts,
            key = { it.id }
        ) { post ->
            val dismissState = rememberSwipeToDismissBoxState()
            if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                viewModel.remove(post)
            }

            SwipeToDismissBox(
                modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null),
                enableDismissFromStartToEnd = false,
                state = dismissState,
                backgroundContent = {}
            ) {
                PostCard(
                    feedPost = post,
                    onViewsClickListener = { statisticItem ->
                        viewModel.updateCount(post, statisticItem)
                    },
                    onLikeClickListener = { _ ->
                        viewModel.changeLikeStatus(post)
                    },
                    onShareClickListener = { statisticItem ->
                        viewModel.updateCount(post, statisticItem)
                    },
                    onCommentClickListener = { _ ->
                        onCommentClickListener(post)
                    }
                )
            }
        }
    }
}