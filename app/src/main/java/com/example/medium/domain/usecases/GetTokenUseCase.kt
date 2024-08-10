package com.example.medium.domain.usecases

import com.example.medium.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTokenUseCase@Inject constructor(private val tokenRepository: TokenRepository){
    operator fun invoke(): Flow<String?>{
        return tokenRepository.getTokenFlow()
    }
}