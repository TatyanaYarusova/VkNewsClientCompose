package com.example.vknewsclientapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vknewsclientapp.ui.MainScreen
import com.example.vknewsclientapp.ui.theme.VkNewsClientAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsClientAppTheme {
               MainScreen()
            }
        }
    }
}