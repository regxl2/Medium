package com.example.medium.domain.usecases

import com.example.medium.domain.model.SignUpBody
import com.example.medium.domain.model.Token
import com.example.medium.domain.repository.AuthRepository
import com.example.medium.domain.util.Resource
import javax.inject.Inject

class SignUpUseCase@Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(body: SignUpBody): Resource<Token>{
        return authRepository.signUp(body = body)
    }
}