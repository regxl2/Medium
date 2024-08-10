package com.example.medium.presentation.authentication

sealed class AuthUiEvent {
    data class OnEmailChange(val email: String) : AuthUiEvent()
    data class OnPasswordChange(val password: String): AuthUiEvent()
    data class OnNameChange(val name: String): AuthUiEvent()
    data object OnSignUp: AuthUiEvent()
    data object OnLogin: AuthUiEvent()
    data object NavigateToLogin: AuthUiEvent()
    data object NavigateToSignUp: AuthUiEvent()
}