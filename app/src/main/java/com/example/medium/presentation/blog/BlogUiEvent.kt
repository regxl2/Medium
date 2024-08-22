package com.example.medium.presentation.blog

import com.example.medium.domain.model.Post

sealed class BlogUiEvent {
    data class OnClickPost(val post: Post): BlogUiEvent()
    data object LoadPosts: BlogUiEvent()
    data object Logout: BlogUiEvent()
    data object OnNavigateBack: BlogUiEvent()
    data object OnClickFloating: BlogUiEvent()
    data object OnError: BlogUiEvent()
}