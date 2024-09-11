package com.example.vknewsclientapp.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vknewsclientapp.domain.FeedPost
import com.example.vknewsclientapp.navigation.AppNavGraph
import com.example.vknewsclientapp.navigation.Screen
import com.example.vknewsclientapp.navigation.rememberNavigationState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    val commentsToPost: MutableState<FeedPost?> = remember {
        mutableStateOf(null)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            navigationState.navigateTo(item.screen.route)
                        },
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
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newsFeedScreenContent = {

                HomeScreen(
                    onCommentClickListener = {
                        commentsToPost.value = it
                        navigationState.navigateTo(Screen.Comments.route)
                    }
                )
            },
            commentsScreenContent = {
                CommentsScreen(
                    onBackPressed = {
                        commentsToPost.value = null
                    },
                    feedPost = commentsToPost.value!!
                )
            },
            favoriteScreenContent = { Text(text = "Favorite") },
            profileScreenContent = { Text(text = "Profile") }
        )
    }
}
