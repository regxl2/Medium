package com.example.medium.presentation.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medium.domain.usecases.LogInUseCase
import com.example.medium.domain.usecases.SetTokenUseCase
import com.example.medium.domain.util.Resource
import com.example.medium.presentation.authentication.AuthUiEvent
import com.example.medium.presentation.authentication.AuthUiState
import com.example.medium.presentation.mappers.toLogInBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel@Inject constructor(private val logInUseCase: LogInUseCase, private val setTokenUseCase: SetTokenUseCase): ViewModel() {
    private val _authUiState = MutableStateFlow(AuthUiState.LogIn())
    val authUiState = _authUiState.asStateFlow()

    fun onEvent(event: AuthUiEvent){
        when(event){
            is AuthUiEvent.OnEmailChange -> changeEmail(event.email)
            is AuthUiEvent.OnPasswordChange -> changePassword(event.password)
            is AuthUiEvent.OnLogin -> onLogin()
            else -> {}
        }
    }
    private fun changePassword(password: String) {
        _authUiState.value = _authUiState.value.copy(password = password, errorMessage = null)
    }

    private fun changeEmail(email: String){
        _authUiState.value = _authUiState.value.copy(email = email, errorMessage = null)
    }

    private fun onLogin(){
        _authUiState.value = _authUiState.value.copy(isLoading = true, errorMessage = null)
        viewModelScope.launch {
            val logInBody = _authUiState.value.toLogInBody()
            when(val resource = logInUseCase(logInBody)){
                is Resource.Success -> {
                    _authUiState.value = _authUiState.value.copy(isLoading = false, onSuccessfulLogIn = true)
                    resource.data?.let { setTokenUseCase(token = it.jwt)}
                }
                is Resource.Error -> {
                    _authUiState.value = _authUiState.value.copy(isLoading = false, errorMessage = resource.message)
                }
            }
        }
    }

    fun onNavigateToBlog(){
        _authUiState.value = _authUiState.value.copy(onSuccessfulLogIn = false)
    }

    fun afterErrorShown(){
        _authUiState.value = _authUiState.value.copy(errorMessage = null)
    }
}