package com.example.medium.domain.repository

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    fun getTokenFlow(): Flow<String?>
    suspend fun setTokenWithInfo(token: String)
    suspend fun resetTokenWithInfo()
}