package com.example.medium.presentation.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medium.domain.usecases.GetTokenUseCase
import com.example.medium.domain.usecases.SetTokenUseCase
import com.example.medium.domain.usecases.SignUpUseCase
import com.example.medium.domain.util.Resource
import com.example.medium.presentation.authentication.AuthUiEvent
import com.example.medium.presentation.authentication.AuthUiState
import com.example.medium.presentation.mappers.toSignUpBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {
    private val _authUiState = MutableStateFlow(AuthUiState.SignUp())
    val authUiState = _authUiState.asStateFlow()

    init {
        authenticate()
    }

    fun onEvent(event: AuthUiEvent) {
        when (event) {
            is AuthUiEvent.OnNameChange -> changeName(event.name)
            is AuthUiEvent.OnEmailChange -> changeEmail(event.email)
            is AuthUiEvent.OnPasswordChange -> changePassword(event.password)
            is AuthUiEvent.OnSignUp -> onSignUp()
            else -> {}
        }
    }

    private fun changeName(name: String) {
        _authUiState.value = _authUiState.value.copy(name = name)
    }

    private fun changePassword(password: String) {
        _authUiState.value = _authUiState.value.copy(password = password)
    }

    private fun changeEmail(email: String) {
        _authUiState.value = _authUiState.value.copy(email = email)
    }

    private fun onSignUp() {
        _authUiState.value = _authUiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val signUpBody = _authUiState.value.toSignUpBody()
            when (val resource = signUpUseCase(signUpBody)) {
                is Resource.Success -> {
                    _authUiState.value =
                        _authUiState.value.copy(isLoading = false, onSuccessfulSignUp = true)
                    resource.data?.let { setTokenUseCase(token = it.jwt) }
                }

                is Resource.Error -> {
                    _authUiState.value =
                        _authUiState.value.copy(isLoading = false, errorMessage = resource.message)
                }
            }
        }
    }

    private fun authenticate() {
        viewModelScope.launch {
            getTokenUseCase()
                .filterNotNull()
                .flowOn(Dispatchers.IO)
                .collect {
                    _authUiState.value = _authUiState.value.copy(onSuccessfulSignUp = true)
                }
        }
    }

    fun onNavigateToBlog() {
        _authUiState.value = _authUiState.value.copy(onSuccessfulSignUp = false)
    }

    fun afterErrorShown(){
        _authUiState.value = _authUiState.value.copy(errorMessage = null)
    }
}