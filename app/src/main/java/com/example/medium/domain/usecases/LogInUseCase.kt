package com.example.medium.domain.usecases

import com.example.medium.domain.model.LogInBody
import com.example.medium.domain.model.Token
import com.example.medium.domain.repository.AuthRepository
import com.example.medium.domain.util.Resource
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val authRepository: AuthRepository){
    suspend operator fun invoke(body: LogInBody):Resource<Token>{
        return authRepository.logIn(body = body)
    }
}