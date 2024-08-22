package com.example.medium.presentation.blog.blogs

import com.example.medium.domain.model.Post

data class BlogsUiState(
    val user: String = "Anonymous",
    val posts: List<Post> = listOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)