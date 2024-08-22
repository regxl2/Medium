package com.example.medium.presentation.blog.addblog

sealed class AddBlogUiEvent{
    data class OnTitleChange(val title: String): AddBlogUiEvent()
    data class OnContentChange(val content: String): AddBlogUiEvent()
    data object OnNavigateBack: AddBlogUiEvent()
    data object OnClickPublish: AddBlogUiEvent()
    data object OnError: AddBlogUiEvent()
}