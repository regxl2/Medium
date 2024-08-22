package com.example.medium.presentation.blog.blogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.example.medium.domain.usecases.GetArticlesUseCase
import com.example.medium.domain.usecases.GetTokenUseCase
import com.example.medium.domain.usecases.LogoutUseCase
import com.example.medium.domain.util.Resource
import com.example.medium.presentation.blog.BlogUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogsViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _blogsUiState = MutableStateFlow(BlogsUiState())
    val blogsUiState = _blogsUiState.asStateFlow()

    init {
        loadBlogs()
    }

    fun onEvent(event: BlogUiEvent){
        when(event){
            is BlogUiEvent.LoadPosts -> { loadBlogs() }
            is BlogUiEvent.Logout -> { logout() }
            is BlogUiEvent.OnError -> {afterErrorShown() }
            else -> Unit
        }
    }

    private fun loadBlogs(){
        viewModelScope.launch {
            _blogsUiState.value = _blogsUiState.value.copy(isLoading = true)
            val token = getTokenUseCase().firstOrNull()
            token?.let {
                val jwt = JWT(token)
                val username = jwt.getClaim("name").asString()?:"Anonymous"
                when(val response = getArticlesUseCase("Bearer $it")){
                    is Resource.Success -> response.data?.let { posts -> _blogsUiState.value = _blogsUiState.value.copy(user = username, posts = posts, isLoading = false) }
                    is Resource.Error -> response.message?.let { message -> _blogsUiState.value = _blogsUiState.value.copy(errorMessage = message, isLoading = false) }
                }
            }
        }
    }

    private fun logout(){
        viewModelScope.launch {
            logoutUseCase()
        }
    }

    private fun afterErrorShown(){
        _blogsUiState.value = _blogsUiState.value.copy(errorMessage = null)
    }
}