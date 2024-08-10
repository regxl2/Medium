package com.example.medium.domain.repository

import com.example.medium.domain.model.LogInBody
import com.example.medium.domain.model.SignUpBody
import com.example.medium.domain.model.Token
import com.example.medium.domain.util.Resource

interface AuthRepository {
    suspend fun signUp(body: SignUpBody):Resource<Token>
    suspend fun logIn(body: LogInBody):Resource<Token>
}