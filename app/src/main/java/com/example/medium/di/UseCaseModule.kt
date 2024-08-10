package com.example.medium.di

import com.example.medium.domain.repository.TokenRepository
import com.example.medium.domain.usecases.SetTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun getSetTokenUseCase(tokenRepository: TokenRepository): SetTokenUseCase{
        return SetTokenUseCase(tokenRepository = tokenRepository)
    }
}