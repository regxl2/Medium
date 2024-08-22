package com.example.medium.presentation.blog.addblog

data class AddBlogUiState(
    val title: String = "",
    val content: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val postId: String? = null
)