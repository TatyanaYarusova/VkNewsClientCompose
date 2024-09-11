package com.example.vknewsclientapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.example.vknewsclientapp.domain.PostComment
import com.example.vknewsclientapp.presentation.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
) {
    val feedPosts = viewModel.feedPosts.observeAsState(listOf())

    if(feedPosts.value.isNotEmpty()) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(20){
                add(PostComment(id = it))
            }
        }
        CommentsScreen(feedPost = feedPosts.value[0], comments = comments )
    }

//    LazyColumn(
//        contentPadding = PaddingValues(
//            top = 8.dp,
//            start = 8.dp,
//            end = 8.dp,
//            bottom = 72.dp
//        ),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(
//            items = feedPosts.value,
//            key = { it.id }
//        ) { post ->
//            val dismissState = rememberSwipeToDismissBoxState()
//            if(dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
//                viewModel.remove(post)
//            }
//
//            SwipeToDismissBox(
//                modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null),
//                enableDismissFromStartToEnd = false,
//                state = dismissState,
//                backgroundContent = {}
//            ){
//                PostCard(
//                    feedPost = post,
//                    onViewsClickListener = { statisticItem ->
//                        viewModel.updateCount(post, statisticItem)
//                    },
//                    onLikeClickListener = { statisticItem ->
//                        viewModel.updateCount(post, statisticItem)
//                    },
//                    onShareClickListener = { statisticItem ->
//                        viewModel.updateCount(post, statisticItem)
//                    },
//                    onCommentClickListener = { statisticItem ->
//                        viewModel.updateCount(post, statisticItem)
//                    }
//                )
//            }
//        }
//    }
}