package com.example.medium.domain.usecases

import com.example.medium.domain.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetTokenUseCase(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke(token: String){
        withContext(Dispatchers.IO){
            tokenRepository.setToken(token = token)
        }
    }
}