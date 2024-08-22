package com.example.medium.domain.usecases

import com.example.medium.domain.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke(){
        withContext(Dispatchers.IO){
            tokenRepository.resetTokenWithInfo()
        }
    }
}