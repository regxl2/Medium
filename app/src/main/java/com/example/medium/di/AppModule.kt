package com.example.medium.di

import android.content.Context
import com.example.medium.data.remote.MediumApi
import com.example.medium.data.repository.TokenRepositoryImpl
import com.example.medium.domain.repository.TokenRepository
import com.example.medium.domain.usecases.GetTokenUseCase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getMediumApi(): MediumApi{
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl("http://10.0.2.2:8787/api/v1/")
            .build()
            .create(MediumApi::class.java)
    }

    @Singleton
    @Provides
    fun getTokenUseCase(tokenRepository: TokenRepository): GetTokenUseCase{
        return GetTokenUseCase(tokenRepository = tokenRepository)
    }
}