package com.example.medium.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface MediumApi {
    @POST("user/signup")
    suspend fun signUp(@Body body: SignUpBodyDto): Response<TokenDto>

    @POST("user/signin")
    suspend fun logIn(@Body body: LogInBodyDto): Response<TokenDto>
}