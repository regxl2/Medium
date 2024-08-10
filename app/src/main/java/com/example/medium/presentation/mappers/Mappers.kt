package com.example.medium.presentation.mappers

import com.example.medium.domain.model.LogInBody
import com.example.medium.domain.model.SignUpBody
import com.example.medium.presentation.authentication.AuthUiState


fun AuthUiState.SignUp.toSignUpBody(): SignUpBody{
    return SignUpBody(name = name, email = email, password = password)
}

fun AuthUiState.LogIn.toLogInBody(): LogInBody{
    return LogInBody(email = email, password = password)
}