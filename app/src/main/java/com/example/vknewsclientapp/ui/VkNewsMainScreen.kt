package com.example.vknewsclientapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vknewsclientapp.domain.FeedPost
import com.example.vknewsclientapp.ui.theme.VkNewsClientAppTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val feedPost = remember {
        mutableStateOf(FeedPost())
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )
                val selectedItemPosition = remember {
                    mutableIntStateOf(0)
                }
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemPosition.intValue == index,
                        onClick = { selectedItemPosition.intValue = index },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            indicatorColor = MaterialTheme.colorScheme.background,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }
            }

        }
    ) {
        PostCard(
            modifier = Modifier.padding(all = 8.dp),
            feedPost = feedPost.value,
            onStatisticItemClickListener = { newItem ->
                val oldStatistic = feedPost.value.statistic
                val newStatistics = oldStatistic.toMutableList().apply {
                    replaceAll { oldItem ->
                        if (oldItem.type == newItem.type){
                            oldItem.copy(count = oldItem.count + 1)
                        } else {
                            oldItem
                        }
                    }
                }
                feedPost.value = feedPost.value.copy(statistic = newStatistics)
            }
        )
    }
}

@Preview
@Composable
fun PreviewMainScreenLight() {
    VkNewsClientAppTheme(darkTheme = false) {
        MainScreen()
    }
}


@Preview
@Composable
fun PreviewMainScreenDark() {
    VkNewsClientAppTheme(darkTheme = true) {
        MainScreen()
    }
}