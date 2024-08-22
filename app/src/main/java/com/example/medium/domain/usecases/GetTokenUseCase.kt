package com.example.medium.domain.usecases

import com.example.medium.domain.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTokenUseCase@Inject constructor(private val tokenRepository: TokenRepository){
    operator fun invoke(): Flow<String?>{
        return tokenRepository.getTokenFlow()
    }
}