package com.example.medium.presentation.authentication

sealed class AuthUiState {
    data class SignUp(
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val errorMessage: String? = null,
        val data: String? = null,
        val isLoading: Boolean = false,
        val onSuccessfulSignUp: Boolean = false
    ) : AuthUiState()

    data class LogIn(
        val email: String = "",
        val password: String = "",
        val errorMessage: String? = null,
        val isLoading: Boolean = false,
        val onSuccessfulLogIn: Boolean = false
    ) : AuthUiState()
}
