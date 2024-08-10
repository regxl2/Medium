package com.example.medium.presentation.blog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Blog(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize().background(color = Color.Red)){
        Text(modifier = Modifier.align(Alignment.Center) ,text = "HelloWorld")
    }
}