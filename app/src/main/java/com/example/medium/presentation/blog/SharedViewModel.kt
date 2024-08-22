package com.example.medium.presentation.blog

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.medium.domain.mappers.toBlogUiState
import com.example.medium.domain.model.Post
import com.example.medium.presentation.blog.viewblog.BlogUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel@Inject constructor(): ViewModel(){
    var blogUiState = mutableStateOf<BlogUiState?>(null)
        private set

    fun onEventBlogs(event: BlogUiEvent){
        when(event){
            is BlogUiEvent.OnClickPost -> updateContent(event.post)
            else -> Unit
        }
    }

    private fun updateContent(post: Post){
        blogUiState.value = post.toBlogUiState()
    }
}