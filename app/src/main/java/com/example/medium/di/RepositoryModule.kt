package com.example.medium.di

import com.example.medium.data.repository.AuthRepositoryImpl
import com.example.medium.data.repository.PostRepositoryImpl
import com.example.medium.data.repository.TokenRepositoryImpl
import com.example.medium.domain.repository.AuthRepository
import com.example.medium.domain.repository.PostRepository
import com.example.medium.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    abstract fun bindTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository

    @Singleton
    @Binds
    abstract fun bindPostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository
}