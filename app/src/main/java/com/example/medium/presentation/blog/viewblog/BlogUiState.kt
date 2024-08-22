package com.example.medium.presentation.blog.viewblog

import androidx.compose.ui.text.AnnotatedString

data class BlogUiState(
    val title: String,
    val content: AnnotatedString,
    val authorName: String
)