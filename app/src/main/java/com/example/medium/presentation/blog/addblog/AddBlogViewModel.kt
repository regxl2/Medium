package com.example.medium.presentation.blog.addblog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medium.domain.repository.PostRepository
import com.example.medium.domain.usecases.PostArticleUseCase
import com.example.medium.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBlogViewModel@Inject constructor(private val postArticleUseCase: PostArticleUseCase): ViewModel() {
    private val _addBlogUiState = MutableStateFlow(AddBlogUiState())
    val addBlogUiState = _addBlogUiState.asStateFlow()

    fun onEvent(event: AddBlogUiEvent){
        when(event){
            is AddBlogUiEvent.OnTitleChange -> { onTitleChange(event.title)}
            is AddBlogUiEvent.OnContentChange -> {onContentChange(event.content)}
            is AddBlogUiEvent.OnClickPublish -> { publishArticle() }
            is AddBlogUiEvent.OnError -> { afterErrorShown() }
            else -> Unit
        }
    }

    private fun onTitleChange(title: String){
        _addBlogUiState.value = _addBlogUiState.value.copy(title = title)
    }

    private fun onContentChange(content: String){
        _addBlogUiState.value = _addBlogUiState.value.copy(content = content)
    }

    private fun publishArticle(){
        viewModelScope.launch {
            _addBlogUiState.value = _addBlogUiState.value.copy(isLoading = true)
            when(val response = postArticleUseCase(title = addBlogUiState.value.title, content = addBlogUiState.value.content)){
                is Resource.Success -> _addBlogUiState.value = _addBlogUiState.value.copy(isLoading = false, postId = response.data?.postId)
                is Resource.Error -> _addBlogUiState.value = _addBlogUiState.value.copy(isLoading = false, errorMessage = response.message)
            }
        }
    }

    private fun afterErrorShown(){
        _addBlogUiState.value = _addBlogUiState.value.copy(errorMessage = null)
    }
}