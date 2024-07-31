package com.example.vknewsclientapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.vknewsclientapp.presentation.MainViewModel
import com.example.vknewsclientapp.ui.MainScreen
import com.example.vknewsclientapp.ui.theme.VkNewsClientAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsClientAppTheme {
               MainScreen(viewModel)
            }
        }
    }
}