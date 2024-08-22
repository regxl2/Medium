package com.example.medium.domain.model

data class PublishBlog(
    val title: String = "untitled",
    val content: String,
    val authorId: String
)